package service

import service.CounterpointService._

import scala.util.Try

class CounterpointService(var randomService: RandomService) {
  def this() = {
    this(new RandomService())
  }

  def generateCantusFirmus(): Try[List[String]] = Try {
    val cantusFirmusLength = randomService.between(MIN_LENGTH, MAX_LENGTH + 1)
    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(MIN_TONIC, MAX_TONIC))
    (1 to cantusFirmusLength).foldLeft(List.empty[String]) { (cantusFirmus, currentNotePositionInCantusFirmus) => {
      if (isFirstNote(currentNotePositionInCantusFirmus) || isLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
        cantusFirmus :+ tonic
      } else {
        cantusFirmus :+ generateNonFirstOrLastCantusFirmusNote(
          cantusFirmusLength,
          tonic,
          currentNotePositionInCantusFirmus,
          cantusFirmus
        )
      }
    }
    }
  }

  def getInMajorKeyCantusFirmusNotes(tonic: String): Seq[String] =
    AVAILABLE_CANTUS_FIRMUS_NOTES.filter(note => {
      noteIsInMajorKey(tonic, note, AVAILABLE_CANTUS_FIRMUS_NOTES.indices
        .filter(interval => intervalIsInMajorKey(interval)))
    })

  private def intervalIsInMajorKey(interval: Int) =
    MAJOR_KEY_INTERVALS.contains(interval % OCTAVE)

  private def noteIsInMajorKey(tonic: String, note: String, majorKeyIntervals: Seq[Int]) =
    majorKeyIntervals
      .map(interval => {
        getNoteForInterval(tonic, interval)
      })
      .contains(note)

  private def getNoteForInterval(tonic: String, interval: Int) =
    AVAILABLE_CANTUS_FIRMUS_NOTES(
      (AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(tonic) + interval) %
        (OCTAVE * 2)
    )

  private def generateNonFirstOrLastCantusFirmusNote(cantusFirmusLength: Int, tonic: String, currentNotePositionInCantusFirmus: Int, cantusFirmus: List[String]): String = {
    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
    if (isPenultimateNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
      generatePenultimateNote(tonic, inMajorKeyCantusFirmusNotes)
    } else {
      generateMiddleCantusFirmusNote(
        cantusFirmusLength,
        tonic,
        inMajorKeyCantusFirmusNotes,
        currentNotePositionInCantusFirmus,
        cantusFirmus
      )
    }
  }

  // "middle" is defined as not the first, last, or second to last note
  private def generateMiddleCantusFirmusNote(cantusFirmusLength: Int, tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], currentNotePositionInCantusFirmus: Int, cantusFirmus: List[String]) = {
    val previousNote = cantusFirmus(currentNotePositionInCantusFirmus - 2)
    val tonicPositionInKey = inMajorKeyCantusFirmusNotes.indexOf(tonic)
    val previousNotePositionInKey = inMajorKeyCantusFirmusNotes.indexOf(previousNote)
    val halfOfInKeyNotesLength = inMajorKeyCantusFirmusNotes.length / 2
    val isTonicLowerHalfOfKey = tonicPositionInKey < halfOfInKeyNotesLength
    val isLastNoteLowerHalfOfKey = previousNotePositionInKey < halfOfInKeyNotesLength
    val isLastNoteLeadingTone: Boolean = isLeadingTone(
      tonicPositionInKey,
      previousNotePositionInKey,
      isTonicLowerHalfOfKey
    )

    if (isLastNoteLeadingTone) {
      handleLastNoteAsLeadingTone(tonic, inMajorKeyCantusFirmusNotes, tonicPositionInKey, halfOfInKeyNotesLength, isTonicLowerHalfOfKey, isLastNoteLowerHalfOfKey)
    } else {
      val leadingTones = inMajorKeyCantusFirmusNotes.filter(note => {
        note.filterNot(c => c.isDigit) == inMajorKeyCantusFirmusNotes(tonicPositionInKey - 1).filterNot(c => c.isDigit)
      })
      val validNotes = filterInvalidNotes(cantusFirmusLength, inMajorKeyCantusFirmusNotes, previousNote, currentNotePositionInCantusFirmus, cantusFirmus)
      if (isAntePenultimateNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
        val availableNotes = generateAntePenultimateNotes(inMajorKeyCantusFirmusNotes, previousNote, tonicPositionInKey, validNotes, leadingTones)
        availableNotes(randomService.nextInt(availableNotes.length))
      } else {
        val availableNotes = FilterHighestNoteAndGuaranteeMelodicConsonances(tonic, previousNote, validNotes)
        if (isFourthToLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
          generateFourthToLastNote(inMajorKeyCantusFirmusNotes, availableNotes, tonicPositionInKey, leadingTones)
        } else {
          availableNotes(randomService.nextInt(availableNotes.length))
        }
      }
    }
  }

  private def FilterHighestNoteAndGuaranteeMelodicConsonances(tonic: String, previousNote: String, notes: Seq[String]) = {
    if (tonic.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES.head.filterNot(c => c.isDigit)) {
      guaranteeMelodicConsonances(previousNote, notes).filter(note => note != AVAILABLE_CANTUS_FIRMUS_NOTES.last)
    } else {
      guaranteeMelodicConsonances(previousNote, notes)
    }
  }

  private def filterInvalidNotes(cantusFirmusLength: Int, inMajorKeyCantusFirmusNotes: Seq[String], previousNote: String, currentNotePositionInCantusFirmus: Int, cantusFirmus: List[String]) = {
    val lowestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).min
    val highestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).max
    val numLargeLeaps = cantusFirmus.zipWithIndex.map {
      case (note, i) =>
        if (i > 0) {
          if (math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i - 1))) > 5) {
            note
          } else {
            ""
          }
        } else {
          ""
        }
    }.count(note => note != "")
    val moreThanATenthAndLastNoteRemoved = inMajorKeyCantusFirmusNotes.filter(note => note != previousNote).filter(note => {
      val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
      noteIdx - lowestNote <= 14 && highestNote - noteIdx <= 14
    })

    if (numLargeLeaps >= 1 || isFourthToLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
      moreThanATenthAndLastNoteRemoved.filter(note => isALeap(previousNote, note))
    } else {
      moreThanATenthAndLastNoteRemoved
    }
  }

  private def isALeap(lastNote: String, note: String) = {
    val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
    val lastNoteIdxInAvailable = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(lastNote)
    math.abs(noteIdx - lastNoteIdxInAvailable) <= 5
  }
  private def generateFourthToLastNote(inMajorKeyCantusFirmusNotes: Seq[String], availableNotes: Seq[String], tonicPositionInKey: Int, leadingTones: Seq[String]) = {
    val leadingTonesAcrossOctaves = leadingTones.filter(note => {
      note.filter(c => c.isDigit) != inMajorKeyCantusFirmusNotes(tonicPositionInKey - 1).filterNot(c => c.isDigit)
    })

    val availableFourthToLastNotes = availableNotes.filter(noteToChooseFrom =>
      generateAntePenultimateNotes(
        inMajorKeyCantusFirmusNotes,
        noteToChooseFrom,
        tonicPositionInKey,
        inMajorKeyCantusFirmusNotes
          .filter(note => note != noteToChooseFrom),
        leadingTonesAcrossOctaves
      ).exists(note => isALeap(noteToChooseFrom, note)) &&
        !leadingTonesAcrossOctaves.contains(noteToChooseFrom)
    )
    availableFourthToLastNotes(randomService.nextInt(availableFourthToLastNotes.length))
  }
  private def generateAntePenultimateNotes(inMajorKeyCantusFirmusNotes: Seq[String], previousNote: String, tonicPositionInKey: Int, notes: Seq[String], leadingTones: Seq[String]) = {
    guaranteeMelodicConsonances(
      inMajorKeyCantusFirmusNotes(tonicPositionInKey - 1),
      guaranteeMelodicConsonances(
        inMajorKeyCantusFirmusNotes(tonicPositionInKey + 1),
        guaranteeMelodicConsonances(
          previousNote,
          notes.filterNot(note => leadingTones.contains(note))
        )
      )
    )
  }

  private def guaranteeMelodicConsonances(previousNote: String, notes: Seq[String]): Seq[String] = {
    notes
      .filter(note => MELODIC_CONSONANCES
        .contains(math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)))
      )
  }
  private def handleLastNoteAsLeadingTone(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], tonicPositionInKey: Int, halfOfInKeyNotesLength: Int, isTonicLowerHalfOfKey: Boolean, isLeadingToneLowerHalfOfKey: Boolean) = {
    if ((isLeadingToneLowerHalfOfKey && isTonicLowerHalfOfKey) || (!isLeadingToneLowerHalfOfKey && !isTonicLowerHalfOfKey) || tonicPositionInKey == inMajorKeyCantusFirmusNotes.length / 2) {
      tonic
    } else if (isLeadingToneLowerHalfOfKey && !isTonicLowerHalfOfKey) {
      inMajorKeyCantusFirmusNotes(tonicPositionInKey - halfOfInKeyNotesLength)
    } else {
      inMajorKeyCantusFirmusNotes(tonicPositionInKey + halfOfInKeyNotesLength)
    }
  }

  private def isAntePenultimateNote(cantusFirmusLength: Int, currentNotePositionInCantusFirmus: Int) = {
    currentNotePositionInCantusFirmus == cantusFirmusLength - 2
  }

  private def isFourthToLastNote(cantusFirmusLength: Int, currentNotePositionInCantusFirmus: Int) = {
    currentNotePositionInCantusFirmus == cantusFirmusLength - 3
  }

  private def isLeadingTone(tonicIdx: Int, noteIdx: Int, isTonicLowerHalf: Boolean): Boolean = {
    val isUnalteredLeadingTone = isDirectLeadingTone(tonicIdx, noteIdx)
    if (isTonicLowerHalf) {
      isUnalteredLeadingTone || isDirectLeadingTone((tonicIdx + 7), noteIdx)
    } else {
      isUnalteredLeadingTone || isDirectLeadingTone((tonicIdx - 7), noteIdx)
    }
  }

  private def isDirectLeadingTone(tonicIdx: Int, noteIdx: Int) = {
    tonicIdx - noteIdx == 1
  }

  private def generatePenultimateNote(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String]): String = {
    if (randomService.nextDouble() >= 0.7) {
      inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) - 1)
    } else {
      inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) + 1)
    }
  }

  private def isPenultimateNote(length: Int, i: Int) = {
    i == length - 1
  }

  private def isLastNote(length: Int, i: Int) = {
    i == length
  }

  private def isFirstNote(i: Int) = {
    i == 1
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
