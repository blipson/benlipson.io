package service

import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, MAJOR_KEY_INTERVALS, MAX_LENGTH, MAX_TONIC, MELODIC_CONSONANCES, MIN_LENGTH, MIN_TONIC, OCTAVE}

import scala.util.{Failure, Success, Try}

class CounterpointRecursiveService(var randomService: RandomService) {
  def this() = {
    this(new RandomService())
  }

  def generateCantusFirmus(): Try[List[String]] = Try {
    val length = randomService.between(MIN_LENGTH, MAX_LENGTH + 1)
    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(MIN_TONIC, MAX_TONIC))
    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
    return Success(generateCantusFirmusRecursive(length, tonic, inMajorKeyCantusFirmusNotes))
  }

  def generateCantusFirmusRecursive(length: Int, tonic: String, inMajorKeyNotes: Seq[String], cantusFirmus: List[String] = List(), invalidNextNotes: List[String] = List(), invalidNotePos: Int = -1): List[String] = {
    if (cantusFirmus.length == length) {
      cantusFirmus
    } else if (isFirstNote(cantusFirmus) || isLastNote(length, cantusFirmus)) {
      generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus :+ tonic)
    } else {
      val newInvalidNextNotes = if (cantusFirmus.length > invalidNotePos) {
        List()
      } else {
        invalidNextNotes
      }
      generateCantusFirmusNote(inMajorKeyNotes, length, cantusFirmus, newInvalidNextNotes) match {
        case Success(nextNote) =>
          generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus :+ nextNote, invalidNextNotes, invalidNotePos)
        case Failure(invalidNoteException) =>
          val invalidNoteMessage = invalidNoteException.getMessage.split(":")
          generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus.dropRight(1), invalidNextNotes :+ invalidNoteMessage.head, invalidNoteMessage.last.toInt)
      }
    }
  }

  def generateCantusFirmusNote(inMajorKeyNotes: Seq[String], length: Int, cantusFirmus: List[String], invalidNotes: Seq[String]): Try[String] = {
    val tonic = cantusFirmus.head
    val universalRulesApplied = applyUniversalRules(inMajorKeyNotes, cantusFirmus, invalidNotes)
    val stepwiseAfterLeapsApplied = applyStepwiseAfterLargeLeapsRule(inMajorKeyNotes, cantusFirmus, universalRulesApplied)
    val availableNotes = applyIndividualRules(inMajorKeyNotes, length, cantusFirmus, tonic, stepwiseAfterLeapsApplied)

    val prev = cantusFirmus.last
    if (availableNotes.isEmpty) {
      Failure(new Exception(s"$prev:${cantusFirmus.length - 1}"))
    } else {
      Success(availableNotes(randomService.nextInt(availableNotes.length)))
    }
  }

  private def getNoteForInterval(tonic: String, interval: Int) =
    AVAILABLE_CANTUS_FIRMUS_NOTES(
      (AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(tonic) + interval) %
        (OCTAVE * 2)
    )

  private def intervalIsInMajorKey(interval: Int) =
    MAJOR_KEY_INTERVALS.contains(interval % OCTAVE)

  private def noteIsInMajorKey(tonic: String, note: String, majorKeyIntervals: Seq[Int]) =
    majorKeyIntervals
      .map(interval => {
        getNoteForInterval(tonic, interval)
      })
      .contains(note)

  private def getInMajorKeyCantusFirmusNotes(tonic: String): Seq[String] =
    AVAILABLE_CANTUS_FIRMUS_NOTES.filter(note => {
      noteIsInMajorKey(tonic, note, AVAILABLE_CANTUS_FIRMUS_NOTES.indices
        .filter(interval => intervalIsInMajorKey(interval)))
    })

  private def isLastNote(length: Int, cantusFirmus: List[String]) = {
    cantusFirmus.length == length - 1
  }

  private def isFirstNote(cantusFirmus: List[String]) = {
    cantusFirmus.isEmpty
  }

  private def applyIndividualRules(inMajorKeyNotes: Seq[String], length: Int, cantusFirmus: List[String], tonic: String, notes: Seq[String]) =
    if (isPenultimateNote(length, cantusFirmus)) {
      notes.filter(note => applyPenultimateStepwiseMotionRule(inMajorKeyNotes, tonic).contains(note))
    } else if (isLeadingTone(inMajorKeyNotes, cantusFirmus, tonic)) {
      notes.filter(note => applyLeadingToneLeadsToTonicRule(cantusFirmus, note))
    } else if (isAntePenultimateNote(length, cantusFirmus)) {
      notes.filter(note => applyAntePenultimateCannotBeLeadingToneRule(inMajorKeyNotes, tonic, note))
    } else {
      notes
    }

  private def applyAntePenultimateCannotBeLeadingToneRule(inMajorKeyNotes: Seq[String], tonic: String, note: String) =
    note.filterNot(c => c.isDigit) != inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1).filterNot(c => c.isDigit)

  private def isAntePenultimateNote(length: Int, cantusFirmus: List[String]) =
    cantusFirmus.length == length - 3

  private def isLeadingTone(inMajorKeyNotes: Seq[String], cantusFirmus: List[String], tonic: String) =
    cantusFirmus.last.filterNot(c => c.isDigit) == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1).filterNot(c => c.isDigit)

  private def applyLeadingToneLeadsToTonicRule(cantusFirmus: List[String], note: String) =
    AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) == 1

  private def applyPenultimateStepwiseMotionRule(inMajorKeyNotes: Seq[String], tonic: String) =
    inMajorKeyNotes.filter(note => {
      note == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1) || note == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) + 1)
    })

  private def isPenultimateNote(length: Int, cantusFirmus: List[String]) =
    cantusFirmus.length == length - 2

  private def applyStepwiseAfterLargeLeapsRule(inMajorKeyNotes: Seq[String], cantusFirmus: List[String], notes: Seq[String]) =
    if (followingALargeLeap(cantusFirmus)) {
      val next = if (isDownwardsMotion(cantusFirmus)) {
        inMajorKeyNotes(inMajorKeyNotes.indexOf(cantusFirmus.last) + 1)
      } else {
        inMajorKeyNotes(inMajorKeyNotes.indexOf(cantusFirmus.last) - 1)
      }
      if (notes.contains(next)) {
        Seq(next)
      } else {
        Seq()
      }
    } else {
      notes
    }

  private def isDownwardsMotion(cantusFirmus: List[String]) =
    AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(cantusFirmus.length - 2)) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) > 0

  private def followingALargeLeap(cantusFirmus: List[String]) =
    cantusFirmus.length > 1 &&
      math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(cantusFirmus.length - 2))) >= 5

  private def applyUniversalRules(inMajorKeyNotes: Seq[String], cantusFirmus: List[String], invalidNotes: Seq[String]) = {
    val lowestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).min
    val highestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).max
    inMajorKeyNotes
      .filter(note => {
        val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
        note != cantusFirmus.last &&
          noteIdx - lowestNote <= 16 && highestNote - noteIdx <= 16 &&
          !invalidNotes.contains(note) &&
          MELODIC_CONSONANCES
            .contains(math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)))
      })
  }
}


object CounterpointService {
  val OCTAVE = 12
  val MIN_LENGTH = 8
  val MAX_LENGTH = 16


  val MAJOR_KEY_INTERVALS = Set(
    0, 2, 4, 5, 7, 9, 11
  )

  val NOTES = List(
    "E",
    "F",
    "F#/Gb",
    "G",
    "G#/Ab",
    "A",
    "A#/Bb",
    "B",
    "C",
    "C#/Db",
    "D",
    "D#/Eb"
  )
  val AVAILABLE_CANTUS_FIRMUS_NOTES: Seq[String] = GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(2, 2)
  val MIN_TONIC: Int = 3
  val MAX_TONIC: Int = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7

  def GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(numOctaves: Int, startOctave: Int): Seq[String] =
    (0 until NOTES.length * numOctaves).map(noteIdx => {
      val stepsAboveC = OCTAVE - NOTES.slice(NOTES.length - NOTES.indexOf("C"), NOTES.length).length
      val currentOctave = startOctave + math.floor((noteIdx + stepsAboveC) / OCTAVE).toInt
      NOTES(noteIdx % NOTES.length).concat(currentOctave.toString)
    }).toList

  def MELODIC_CONSONANCES = Set(
    1, 2, 3, 4, 5, 7, 8, 9, 12
  )
}

