package service

import service.CounterpointService._

import scala.util.Try

class CounterpointService(var randomService: RandomService) {
  def this() = {
    this(new RandomService())
  }

  def generateCantusFirmus(): Try[List[String]] = Try {
    val length = randomService.between(MIN_LENGTH, MAX_LENGTH + 1)
    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(MIN_TONIC, MAX_TONIC))
    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
    (1 to length).foldLeft(List.empty[String]) { (acc, currentNoteIdx) => {
      if (isFirstNote(currentNoteIdx) || isLastNote(length, currentNoteIdx)) {
        acc :+ tonic
      } else {
        val lastNote = acc(currentNoteIdx - 2)
        val lowestNote = acc.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).min
        val highestNote = acc.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).max
        val numLeaps = acc.zipWithIndex.map {
          case (note, i) =>
            if (i > 0) {
              if (math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(acc(i - 1))) > 5) {
                note
              } else {
                ""
              }
            } else {
              ""
            }
        }.count(note => note != "")
        acc :+ generateNonFirstOrLastCantusFirmusNote(
          length,
          tonic,
          inMajorKeyCantusFirmusNotes,
          lastNote,
          currentNoteIdx,
          lowestNote,
          highestNote,
          numLeaps
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

  private def generateNonFirstOrLastCantusFirmusNote(length: Int, tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], lastNote: String, currentNoteIdx: Int, lowestNote: Int, highestNote: Int, numLeaps: Int): String = {
    val tonicIdx = inMajorKeyCantusFirmusNotes.indexOf(tonic)
    if (isPenultimateNote(length, currentNoteIdx)) {
      pickPenultimateNote(tonic, inMajorKeyCantusFirmusNotes)
    } else {
      generateMiddleCantusFirmusNote(
        length,
        tonic,
        inMajorKeyCantusFirmusNotes,
        lastNote,
        currentNoteIdx,
        tonicIdx,
        lowestNote,
        highestNote,
        numLeaps
      )
    }
  }

  // "middle" is defined as not the first, last, or second to last note
  private def generateMiddleCantusFirmusNote(length: Int, tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], lastNote: String, currentNoteIdx: Int, tonicIdx: Int, lowestNote: Int, highestNote: Int, numLeaps: Int) = {
    val lastNoteIdx = inMajorKeyCantusFirmusNotes.indexOf(lastNote)
    val halfOfInKeyNotesLen = inMajorKeyCantusFirmusNotes.length / 2
    val isTonicLowerHalf = tonicIdx < halfOfInKeyNotesLen
    val isLastNoteLowerHalf = lastNoteIdx < halfOfInKeyNotesLen
    val isLastNoteLeadingTone: Boolean = isLeadingTone(
      tonicIdx,
      lastNoteIdx,
      isTonicLowerHalf
    )
    if (isLastNoteLeadingTone) {
      handleLastNoteAsLeadingTone(tonic, inMajorKeyCantusFirmusNotes, tonicIdx, halfOfInKeyNotesLen, isTonicLowerHalf, isLastNoteLowerHalf)
    } else {
      val leadingTones = inMajorKeyCantusFirmusNotes.filter(note => {
        note.filterNot(c => c.isDigit) == inMajorKeyCantusFirmusNotes(tonicIdx - 1).filterNot(c => c.isDigit)
      })
      val validNotes = filterInvalidNotes(length, inMajorKeyCantusFirmusNotes, lastNote, currentNoteIdx, lowestNote, highestNote, numLeaps)
      if (isAntePenultimateNote(length, currentNoteIdx)) {
        val notesToChooseFrom = generateAntePenultimateNotes(inMajorKeyCantusFirmusNotes, lastNote, tonicIdx, validNotes, leadingTones)
        notesToChooseFrom(randomService.nextInt(notesToChooseFrom.length))
      } else {
        val notesToChooseFrom = FilterHighestNoteAndGuaranteeMelodicConsonances(tonic, lastNote, validNotes)
        if (isFourthToLastNote(length, currentNoteIdx)) {
          generateFourthToLastNote(inMajorKeyCantusFirmusNotes, notesToChooseFrom, tonicIdx, leadingTones)
        } else {
          notesToChooseFrom(randomService.nextInt(notesToChooseFrom.length))
        }
      }
    }
  }

  private def FilterHighestNoteAndGuaranteeMelodicConsonances(tonic: String, lastNote: String, validNotes: Seq[String]) = {
    if (tonic.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES.head.filterNot(c => c.isDigit)) {
      guaranteeMelodicConsonances(lastNote, validNotes).filter(note => note != AVAILABLE_CANTUS_FIRMUS_NOTES.last)
    } else {
      guaranteeMelodicConsonances(lastNote, validNotes)
    }
  }

  private def filterInvalidNotes(length: Int, inMajorKeyCantusFirmusNotes: Seq[String], lastNote: String, currentNoteIdx: Int, lowestNote: Int, highestNote: Int, numLeaps: Int) = {
    val moreThanATenthAndLastNoteRemoved = inMajorKeyCantusFirmusNotes.filter(note => note != lastNote).filter(note => {
      val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
      noteIdx - lowestNote <= 14 && highestNote - noteIdx <= 14
    })

    if (numLeaps >= 1 || isFourthToLastNote(length, currentNoteIdx)) {
      moreThanATenthAndLastNoteRemoved.filter(note => isALeap(lastNote, note))
    } else {
      moreThanATenthAndLastNoteRemoved
    }
  }

  private def isALeap(lastNote: String, note: String) = {
    val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
    val lastNoteIdxInAvailable = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(lastNote)
    math.abs(noteIdx - lastNoteIdxInAvailable) <= 5
  }

  private def generateFourthToLastNote(inMajorKeyCantusFirmusNotes: Seq[String], notesToChooseFrom: Seq[String], tonicIdx: Int, leadingTones: Seq[String]) = {
    val differentOctaveLeadingTones = leadingTones.filter(note => {
      note.filter(c => c.isDigit) != inMajorKeyCantusFirmusNotes(tonicIdx - 1).filterNot(c => c.isDigit)
    })

    val lookAheadNotesToChooseFrom = notesToChooseFrom.filter(noteToChooseFrom =>
      generateAntePenultimateNotes(
        inMajorKeyCantusFirmusNotes,
        noteToChooseFrom,
        tonicIdx,
        inMajorKeyCantusFirmusNotes
          .filter(note => note != noteToChooseFrom),
        differentOctaveLeadingTones
      ).exists(note => isALeap(noteToChooseFrom, note)) &&
        !differentOctaveLeadingTones.contains(noteToChooseFrom)
    )
    lookAheadNotesToChooseFrom(randomService.nextInt(lookAheadNotesToChooseFrom.length))
  }

  private def generateAntePenultimateNotes(inMajorKeyCantusFirmusNotes: Seq[String], lastNote: String, tonicIdx: Int, notes: Seq[String], leadingTones: Seq[String]) = {
    guaranteeMelodicConsonances(
      inMajorKeyCantusFirmusNotes(tonicIdx - 1),
      guaranteeMelodicConsonances(
        inMajorKeyCantusFirmusNotes(tonicIdx + 1),
        guaranteeMelodicConsonances(
          lastNote,
          notes.filterNot(note => leadingTones.contains(note))
        )
      )
    )
  }

  private def guaranteeMelodicConsonances(lastNote: String, availableNotes: Seq[String]): Seq[String] = {
    availableNotes
      .filter(note => MELODIC_CONSONANCES
        .contains(math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(lastNote) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)))
      )
  }

  private def handleLastNoteAsLeadingTone(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], tonicIdx: Int, halfOfInKeyNotesLen: Int, isTonicLowerHalf: Boolean, isLeadingToneLowerHalf: Boolean) = {
    if ((isLeadingToneLowerHalf && isTonicLowerHalf) || (!isLeadingToneLowerHalf && !isTonicLowerHalf) || tonicIdx == inMajorKeyCantusFirmusNotes.length / 2) {
      tonic
    } else if (isLeadingToneLowerHalf && !isTonicLowerHalf) {
      inMajorKeyCantusFirmusNotes(tonicIdx - halfOfInKeyNotesLen)
    } else {
      inMajorKeyCantusFirmusNotes(tonicIdx + halfOfInKeyNotesLen)
    }
  }

  private def isAntePenultimateNote(length: Int, i: Int) = {
    i == length - 2
  }

  private def isFourthToLastNote(length: Int, i: Int) = {
    i == length - 3
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

  private def pickPenultimateNote(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String]): String = {
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
