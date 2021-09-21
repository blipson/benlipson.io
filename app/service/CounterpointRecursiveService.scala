package service

import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, MAJOR_KEY_INTERVALS, MAX_LENGTH, MAX_TONIC, MELODIC_CONSONANCES, MIN_LENGTH, MIN_TONIC, OCTAVE}

import scala.util.{Failure, Success, Try}

class CounterpointRecursiveService(var randomService: RandomService) {
  def this() = {
    this(new RandomService())
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

  def generateCantusFirmus(): Try[List[String]] = Try {
    val length = randomService.between(MIN_LENGTH, MAX_LENGTH + 1)
    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(MIN_TONIC, MAX_TONIC))
    // must be in major key
    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
    println("length: " + length)
    return Success(generateCantusFirmusRecursive(length, tonic, inMajorKeyCantusFirmusNotes))
  }

  def generateCantusFirmusRecursive(length: Int, tonic: String, inMajorKeyNotes: Seq[String], cantusFirmus: List[String] = List(), invalidNextNotes: List[String] = List(), invalidNotePos: Int = -1): List[String] = {
    println(cantusFirmus)
    if (cantusFirmus.length == length) {
      cantusFirmus
    } else if (cantusFirmus.isEmpty || cantusFirmus.length == length - 1) {
      // first and last note must be tonic
      generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus :+ tonic, List.empty)
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
          val invalidNote = invalidNoteMessage.head
          val invalidNotePos = invalidNoteMessage.last.toInt
          generateCantusFirmusRecursive(length, tonic, inMajorKeyNotes, cantusFirmus.dropRight(1), invalidNextNotes :+ invalidNote, invalidNotePos)
      }
    }
  }

  def generateCantusFirmusNote(inMajorKeyNotes: Seq[String], length: Int, cantusFirmus: List[String], invalidNotes: Seq[String]): Try[String] = {
    val tonic = cantusFirmus.head
    val lowestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).min
    val highestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).max



    // ensure no repeated notes
    // ensure melodic consonances
    // ensure range is not greater than a 10th
    val nonRepeatedMelodicConsonances = inMajorKeyNotes
      .filter(note => {
        val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
        note != cantusFirmus.last &&
        noteIdx - lowestNote <= 16 && highestNote - noteIdx <= 16 &&
        !invalidNotes.contains(note) &&
          MELODIC_CONSONANCES
            .contains(math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)))
      })


    val stepwiseAfterLeaps: Seq[String] = if (
      cantusFirmus.length > 1 &&
        math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(cantusFirmus.length - 2))) >= 5) {
      // note after a leap must be stepwise motion in the opposite direction
      if (AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(cantusFirmus.length - 2)) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) > 0) {
        // the 2nd note is lower than the first
        val next = inMajorKeyNotes(inMajorKeyNotes.indexOf(cantusFirmus.last) + 1)
        if (nonRepeatedMelodicConsonances.contains(next)) {
          Seq(next)
        } else {
          Seq()
        }
      } else {
        // the 2nd note is higher than the first
        val next = inMajorKeyNotes(inMajorKeyNotes.indexOf(cantusFirmus.last) - 1)
        if (nonRepeatedMelodicConsonances.contains(next)) {
          Seq(next)
        } else {
          Seq()
        }
      }
    } else {
      nonRepeatedMelodicConsonances
    }



    val availableNotes = if (cantusFirmus.length == length - 2) {
      // penultimate note must be either 2 or leading tone
      val penultimateNotes = inMajorKeyNotes.filter(note => {
        note == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1) || note == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) + 1)
      })
      stepwiseAfterLeaps.filter(note => penultimateNotes.contains(note))
    } else if (cantusFirmus.last.filterNot(c => c.isDigit) == inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1).filterNot(c => c.isDigit)) {
      // last note was the leading tone so it must lead to the tonic of the same octave
      stepwiseAfterLeaps.filter(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus.last) == 1)
    } else if (cantusFirmus.length == length - 3) {
      // ante penultimate note cannot be the leading tone
      stepwiseAfterLeaps.filter(note => note.filterNot(c => c.isDigit) != inMajorKeyNotes(inMajorKeyNotes.indexOf(tonic) - 1).filterNot(c => c.isDigit))
    } else {
      stepwiseAfterLeaps
    }
    val prev = cantusFirmus.last
    if (availableNotes.isEmpty) {
      Failure(new Exception(s"$prev:${cantusFirmus.length - 1}"))
    } else {
      Success(availableNotes(randomService.nextInt(availableNotes.length)))
    }
  }
}
