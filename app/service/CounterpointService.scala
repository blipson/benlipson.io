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
    val cantusFirmus = generateCantusFirmusRecursive(length, tonic, inMajorKeyCantusFirmusNotes)
    return if (cantusFirmus.nonEmpty) {
      Success(cantusFirmus)
    } else {
      Failure(new Exception("Could not generate cantus firmus."))
    }
  }

//  var layers: scala.collection.mutable.ArrayBuffer[Int] = scala.collection.mutable.ArrayBuffer.empty[Int]
//  var count = 0

  def generateCantusFirmusRecursive(length: Int, tonic: String, inMajorKeyNotes: List[String], cantusFirmus: List[String] = List(), invalidLines: List[List[String]] = List(), invalidNotePos: Int = -1): List[String] = {
    println(cantusFirmus)
//    layers.addOne(cantusFirmus.length)
//    if (cantusFirmus.length < layers.max - 1) {
//      count += 1
//      if (count > 2) {
//        val x = 1
//      }
//    }
    if (cantusFirmus.length == length) {
      cantusFirmus
    } else {
      generateCantusFirmusNote(length, tonic, inMajorKeyNotes, cantusFirmus, invalidLines) match {
        case Success(nextNote) =>
          generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus :+ nextNote, invalidLines, invalidNotePos)
        case Failure(invalidNoteException) =>
          val invalidNoteMessage = invalidNoteException.getMessage
          if (invalidNoteMessage == "Can not generate cantus firmus.") {
            cantusFirmus
          } else {
            generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus.dropRight(1), invalidLines :+  invalidNoteMessage.filter(c => !"List()".contains(c)).replace(" ", "").split(",").toList, invalidNoteMessage.last.toInt)
          }
      }
    }
  }

  def generateCantusFirmusNote(length: Int, tonic: String, inMajorKeyNotes: List[String], cantusFirmus: List[String], invalidLines: List[List[String]]): Try[String] = {
    if (isFirstNote(cantusFirmus)) {
      if (allSecondNotesHaveBeenTried(tonic, inMajorKeyNotes, invalidLines)) {
        Failure(new Exception(s"Can not generate cantus firmus."))
      } else {
        Success(tonic)
      }
    } else {
      val universalRulesApplied = applyUniversalRules(inMajorKeyNotes, cantusFirmus, invalidLines)
      val leapsRulesApplied = applyLeapsRules(inMajorKeyNotes, cantusFirmus, universalRulesApplied)
      val individualRulesApplied = applyIndividualRules(inMajorKeyNotes, length, cantusFirmus, tonic, leapsRulesApplied)

      //    val availableNotes = if (cantusFirmus.length > (length / 3) && cantusFirmus.length < (length - (length / 3))) {
      val availableNotes = individualRulesApplied

      if (availableNotes.isEmpty) {
        Failure(new Exception(s"$cantusFirmus"))
      } else {
        Success(availableNotes(randomService.nextInt(availableNotes.length)))
      }
    }
  }

  private def allSecondNotesHaveBeenTried(tonic: String, inMajorKeyNotes: List[String], invalidLines: List[List[String]]) = {
    invalidLines.filter(line => line.length > 1).map(line => line(1)).distinct.length == inMajorKeyNotes.count(note => isMelodicConsonance(tonic, note))
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

  def getInMajorKeyCantusFirmusNotes(tonic: String): List[String] =
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
    if (isLastNote(length, cantusFirmus)) {
      notes.filter(note => applyFinalNoteAsTonicRule(inMajorKeyNotes, cantusFirmus, tonic, note))
    } else if (isPenultimateNote(length, cantusFirmus)) {
      notes.filter(note => applyPenultimateStepwiseMotionRule(inMajorKeyNotes, tonic).contains(note))
    } else if (isLeadingTone(inMajorKeyNotes, cantusFirmus, tonic)) {
      notes.filter(note => applyLeadingToneLeadsToTonicRule(cantusFirmus, note))
    } else if (isAntePenultimateNote(length, cantusFirmus)) {
      notes.filter(note => applyAntePenultimateCannotBeLeadingToneRule(inMajorKeyNotes, tonic, note))
    } else {
      notes
    }

  private def applyFinalNoteAsTonicRule(inMajorKeyNotes: Seq[String], cantusFirmus: List[String], tonic: String, note: String) =
    (note.filterNot(c => c.isDigit) == tonic.filterNot(c => c.isDigit)) && (math.abs(inMajorKeyNotes.indexOf(cantusFirmus.last) - inMajorKeyNotes.indexOf(note)) == 1)

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
      val noteWithoutOctave = note.filterNot(c => c.isDigit)
      noteWithoutOctave == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1).filterNot(c => c.isDigit) ||
        noteWithoutOctave == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) + 1).filterNot(c => c.isDigit)
    })

  private def isPenultimateNote(length: Int, cantusFirmus: List[String]) =
    cantusFirmus.length == length - 2

  private def applyLeapsRules(inMajorKeyNotes: List[String], cantusFirmus: List[String], notes: Seq[String]) =
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
    } else if (followingALeap(cantusFirmus, inMajorKeyNotes)) {
      if (followingTwoLeaps(cantusFirmus, inMajorKeyNotes)) {
        notes.filter(note => !isALeap(note, cantusFirmus.last, inMajorKeyNotes))
      } else {
        val previousLeapDirection = if (inMajorKeyNotes.indexOf(cantusFirmus(cantusFirmus.length - 2)) - inMajorKeyNotes.indexOf(cantusFirmus.last) > 0) {
          "down"
        } else {
          "up"
        }
        notes.filter(note => {
          val secondLeapDirection = if (inMajorKeyNotes.indexOf(cantusFirmus.last) - inMajorKeyNotes.indexOf(note) > 0) {
            "down"
          } else {
            "up"
          }
          previousLeapDirection != secondLeapDirection
        })
      }
    } else {
      notes
    }

  private def isDownwardsMotion(cantusFirmus: List[String]) =
    AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(cantusFirmus.length - 2)) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) > 0

  private def followingALargeLeap(cantusFirmus: List[String]) =
    cantusFirmus.length > 1 &&
      math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(cantusFirmus.length - 2))) >= 5

  private def followingALeap(cantusFirmus: List[String], inMajorKeyNotes: List[String]) =
    cantusFirmus.length > 1 &&
      isALeap(cantusFirmus.last, cantusFirmus(cantusFirmus.length - 2), inMajorKeyNotes)

  private def followingTwoLeaps(cantusFirmus: List[String], inMajorKeyNotes: List[String]) =
    cantusFirmus.length > 2 &&
      isALeap(cantusFirmus(cantusFirmus.length - 2), cantusFirmus(cantusFirmus.length - 3), inMajorKeyNotes) &&
      isALeap(cantusFirmus.last, cantusFirmus(cantusFirmus.length - 2), inMajorKeyNotes)

  private def isALeap(note: String, prevNote: String, inMajorKeyNotes: List[String]): Boolean =
    math.abs(inMajorKeyNotes.indexOf(note) - inMajorKeyNotes.indexOf(prevNote)) > 1

  private def applyUniversalRules(notes: Seq[String], cantusFirmus: List[String], invalidLines: List[List[String]]) = {
    val lowestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).min
    val highestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).max

    notes
      .filter(note => {
        val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
        note != cantusFirmus.last &&
          noteIdx - lowestNote <= 16 && highestNote - noteIdx <= 16 &&
          !invalidLines.contains(cantusFirmus :+ note) &&
          note != cantusFirmus.maxBy(cantusFirmusNote => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmusNote)) &&
          isMelodicConsonance(cantusFirmus.last, note)
      })
  }

  private def isMelodicConsonance(lastNote: String, note: String) = {
    MELODIC_CONSONANCES
      .contains(math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(lastNote) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)))
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
  val AVAILABLE_CANTUS_FIRMUS_NOTES: List[String] = GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(2, 2)
  val MIN_TONIC: Int = 3
  val MAX_TONIC: Int = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7

  def GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(numOctaves: Int, startOctave: Int): List[String] =
    (0 until NOTES.length * numOctaves).map(noteIdx => {
      val stepsAboveC = OCTAVE - NOTES.slice(NOTES.length - NOTES.indexOf("C"), NOTES.length).length
      val currentOctave = startOctave + math.floor((noteIdx + stepsAboveC) / OCTAVE).toInt
      NOTES(noteIdx % NOTES.length).concat(currentOctave.toString)
    }).toList

  def MELODIC_CONSONANCES = Set(
    1, 2, 3, 4, 5, 7, 8, 9, 12
  )
}

