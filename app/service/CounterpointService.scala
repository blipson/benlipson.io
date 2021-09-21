//package service
//
//import service.CounterpointService._
//
//import scala.util.Try
//
//class CounterpointService(var randomService: RandomService) {
//  def this() = {
//    this(new RandomService())
//  }
//
//  def generateCantusFirmus(): Try[List[String]] = Try {
//    val cantusFirmusLength = randomService.between(MIN_LENGTH, MAX_LENGTH + 1)
//    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(MIN_TONIC, MAX_TONIC))
//    println("len: " + cantusFirmusLength)
//    // make this index start at 0 you halfwit
//    (1 to cantusFirmusLength).foldLeft(List.empty[String]) { (cantusFirmus, currentNotePositionInCantusFirmus) => {
//      println(cantusFirmus)
//      if (isFirstNote(currentNotePositionInCantusFirmus) || isLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
//        cantusFirmus :+ tonic
//      } else {
//        cantusFirmus :+ generateNonFirstOrLastCantusFirmusNote(
//          cantusFirmusLength,
//          tonic,
//          currentNotePositionInCantusFirmus,
//          cantusFirmus
//        )
//      }
//    }}
//  }
//
//  def getInMajorKeyCantusFirmusNotes(tonic: String): Seq[String] =
//    AVAILABLE_CANTUS_FIRMUS_NOTES.filter(note => {
//      noteIsInMajorKey(tonic, note, AVAILABLE_CANTUS_FIRMUS_NOTES.indices
//        .filter(interval => intervalIsInMajorKey(interval)))
//    })
//
//  private def intervalIsInMajorKey(interval: Int) =
//    MAJOR_KEY_INTERVALS.contains(interval % OCTAVE)
//
//  private def noteIsInMajorKey(tonic: String, note: String, majorKeyIntervals: Seq[Int]) =
//    majorKeyIntervals
//      .map(interval => {
//        getNoteForInterval(tonic, interval)
//      })
//      .contains(note)
//
//  private def getNoteForInterval(tonic: String, interval: Int) =
//    AVAILABLE_CANTUS_FIRMUS_NOTES(
//      (AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(tonic) + interval) %
//        (OCTAVE * 2)
//    )
//
//  private def generateNonFirstOrLastCantusFirmusNote(cantusFirmusLength: Int, tonic: String, currentNotePositionInCantusFirmus: Int, cantusFirmus: List[String]): String = {
//    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
//    val previousNote = cantusFirmus(currentNotePositionInCantusFirmus - 2)
//    if (isPenultimateNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
//      generatePenultimateNote(tonic, inMajorKeyCantusFirmusNotes, previousNote)
//    } else {
//      generateMiddleCantusFirmusNote(
//        cantusFirmusLength,
//        tonic,
//        inMajorKeyCantusFirmusNotes,
//        currentNotePositionInCantusFirmus,
//        cantusFirmus,
//        previousNote
//      )
//    }
//  }
//
//  // "middle" is defined as not the first, last, or second to last note
//  private def generateMiddleCantusFirmusNote(cantusFirmusLength: Int, tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], currentNotePositionInCantusFirmus: Int, cantusFirmus: List[String], previousNote: String) = {
//    val tonicPositionInKey = inMajorKeyCantusFirmusNotes.indexOf(tonic)
//    val previousNotePositionInKey = inMajorKeyCantusFirmusNotes.indexOf(previousNote)
//    val halfOfInKeyNotesLength = inMajorKeyCantusFirmusNotes.length / 2
//    val isTonicLowerHalfOfKey = tonicPositionInKey < halfOfInKeyNotesLength
//    val isLastNoteLowerHalfOfKey = previousNotePositionInKey < halfOfInKeyNotesLength
//    val isLastNoteLeadingTone: Boolean = isLeadingTone(
//      tonicPositionInKey,
//      previousNotePositionInKey,
//      isTonicLowerHalfOfKey
//    )
//
//    if (isLastNoteLeadingTone) {
//      handleLastNoteAsLeadingTone(tonic, inMajorKeyCantusFirmusNotes, tonicPositionInKey, halfOfInKeyNotesLength, isTonicLowerHalfOfKey, isLastNoteLowerHalfOfKey)
//    } else {
//      val lastNoteWasALeap = (cantusFirmus.length > 1 && math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(currentNotePositionInCantusFirmus - 3)) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote)) > 4)
//      val leadingTones = inMajorKeyCantusFirmusNotes.filter(note => {
//        note.filterNot(c => c.isDigit) == inMajorKeyCantusFirmusNotes(tonicPositionInKey - 1).filterNot(c => c.isDigit)
//      })
//      val validNotes = filterInvalidNotes(cantusFirmusLength, inMajorKeyCantusFirmusNotes, previousNote, currentNotePositionInCantusFirmus, cantusFirmus)
//
//      val newValidNotes = if (currentNotePositionInCantusFirmus >= cantusFirmusLength - 5) {
//        validNotes.filterNot(note => note.filterNot(c => c.isDigit) == tonic.filterNot(c => c.isDigit) &&
//          math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote)) > 4
//        )
//      } else {
//        validNotes
//      }
//
//      val newNewValidNotes = if (currentNotePositionInCantusFirmus >= cantusFirmusLength - 6) {
//        val sixth = AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(tonic) - 3)
//        newValidNotes.filterNot(note => note.filterNot(c => c.isDigit) == sixth.filterNot(c => c.isDigit) &&
//          AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) > 4
//        )
//      } else {
//        newValidNotes
//      }
//
//      if (lastNoteWasALeap) {
//        if (AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(currentNotePositionInCantusFirmus - 3)) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote) > 0) {
//          // the 2nd note is lower than the first
//          inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(previousNote) + 1)
//        } else {
//          // the 2nd note is higher than the first
//          inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(previousNote) - 1)
//        }
//      } else {
//        if (isAntePenultimateNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
//          val availableNotes = generateAntePenultimateNotes(inMajorKeyCantusFirmusNotes, previousNote, tonicPositionInKey, newNewValidNotes, leadingTones)
//          availableNotes(randomService.nextInt(availableNotes.length))
//        } else {
//          val availableNotes = FilterHighestNoteAndGuaranteeMelodicConsonances(tonic, previousNote, newNewValidNotes)
//          if (isFourthToLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
//            generateFourthToLastNote(inMajorKeyCantusFirmusNotes, availableNotes, tonicPositionInKey, leadingTones)
//          } else if (isFifthToLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
//            val leadingTonesRemoved = availableNotes.filter(note => !leadingTones.contains(note) && math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote)) <= 4)
//            // remove leaps
//            leadingTonesRemoved(randomService.nextInt(leadingTonesRemoved.length))
//          } else {
//            // you can't leap to the leading tone from below
//            availableNotes(randomService.nextInt(availableNotes.length))
//          }
//        }
//      }
//    }
//  }
//
//  private def FilterHighestNoteAndGuaranteeMelodicConsonances(tonic: String, previousNote: String, notes: Seq[String]) = {
//    if (tonic.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES.head.filterNot(c => c.isDigit)) {
//      guaranteeMelodicConsonances(previousNote, notes).filter(note => note != AVAILABLE_CANTUS_FIRMUS_NOTES.last)
//    } else {
//      guaranteeMelodicConsonances(previousNote, notes)
//    }
//  }
//
//  private def filterInvalidNotes(cantusFirmusLength: Int, inMajorKeyCantusFirmusNotes: Seq[String], previousNote: String, currentNotePositionInCantusFirmus: Int, cantusFirmus: List[String]) = {
//    val lowestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).min
//    val highestNote = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)).max
//    val numLargeLeaps = cantusFirmus.zipWithIndex.map {
//      case (note, i) =>
//        if (i > 0) {
//          if (math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i - 1))) > 5) {
//            note
//          } else {
//            ""
//          }
//        } else {
//          ""
//        }
//    }.count(note => note != "")
//    val moreThanATenthAndLastNoteRemoved = inMajorKeyCantusFirmusNotes.filter(note => note != previousNote).filter(note => {
//      val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
//      noteIdx - lowestNote <= 14 && highestNote - noteIdx <= 14
//    })
//
//    if (numLargeLeaps >= 1 || isFourthToLastNote(cantusFirmusLength, currentNotePositionInCantusFirmus)) {
//      moreThanATenthAndLastNoteRemoved.filter(note => isALeap(previousNote, note))
//    } else {
//      moreThanATenthAndLastNoteRemoved
//    }
//  }
//
//  private def isALeap(lastNote: String, note: String) = {
//    val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
//    val lastNoteIdxInAvailable = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(lastNote)
//    math.abs(noteIdx - lastNoteIdxInAvailable) <= 5
//  }
//  private def generateFourthToLastNote(inMajorKeyCantusFirmusNotes: Seq[String], availableNotes: Seq[String], tonicPositionInKey: Int, leadingTones: Seq[String]) = {
//    val leadingTonesAcrossOctaves = leadingTones.filter(note => {
//      note.filter(c => c.isDigit) != inMajorKeyCantusFirmusNotes(tonicPositionInKey - 1).filterNot(c => c.isDigit)
//    })
//
//    val availableFourthToLastNotes = availableNotes.filter(noteToChooseFrom =>
//      generateAntePenultimateNotes(
//        inMajorKeyCantusFirmusNotes,
//        noteToChooseFrom,
//        tonicPositionInKey,
//        inMajorKeyCantusFirmusNotes
//          .filter(note => note != noteToChooseFrom),
//        leadingTonesAcrossOctaves
//      ).exists(note => isALeap(noteToChooseFrom, note)) &&
//        !leadingTonesAcrossOctaves.contains(noteToChooseFrom)
//    )
//    availableFourthToLastNotes(randomService.nextInt(availableFourthToLastNotes.length))
//  }
//  private def generateAntePenultimateNotes(inMajorKeyCantusFirmusNotes: Seq[String], previousNote: String, tonicPositionInKey: Int, notes: Seq[String], leadingTones: Seq[String]) = {
//    val debug = guaranteeMelodicConsonances(
//      inMajorKeyCantusFirmusNotes(tonicPositionInKey - 1),
//      guaranteeMelodicConsonances(
//        inMajorKeyCantusFirmusNotes(tonicPositionInKey + 1),
//        guaranteeMelodicConsonances(
//          previousNote,
//          notes.filterNot(note => leadingTones.contains(note))
//        )
//      )
//    )
//    debug.filter(note => {
//      math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote)) <= 4
//    })
//  }
//
//  private def guaranteeMelodicConsonances(previousNote: String, notes: Seq[String]): Seq[String] = {
//    notes
//      .filter(note => MELODIC_CONSONANCES
//        .contains(math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)))
//      )
//  }
//  private def handleLastNoteAsLeadingTone(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], tonicPositionInKey: Int, halfOfInKeyNotesLength: Int, isTonicLowerHalfOfKey: Boolean, isLeadingToneLowerHalfOfKey: Boolean) = {
//    if ((isLeadingToneLowerHalfOfKey && isTonicLowerHalfOfKey) || (!isLeadingToneLowerHalfOfKey && !isTonicLowerHalfOfKey) || tonicPositionInKey == inMajorKeyCantusFirmusNotes.length / 2) {
//      tonic
//    } else if (isLeadingToneLowerHalfOfKey && !isTonicLowerHalfOfKey) {
//      inMajorKeyCantusFirmusNotes(tonicPositionInKey - halfOfInKeyNotesLength)
//    } else {
//      inMajorKeyCantusFirmusNotes(tonicPositionInKey + halfOfInKeyNotesLength)
//    }
//  }
//
//  private def isAntePenultimateNote(cantusFirmusLength: Int, currentNotePositionInCantusFirmus: Int) = {
//    currentNotePositionInCantusFirmus == cantusFirmusLength - 2
//  }
//
//  private def isFourthToLastNote(cantusFirmusLength: Int, currentNotePositionInCantusFirmus: Int) = {
//    currentNotePositionInCantusFirmus == cantusFirmusLength - 3
//  }
//
//  private def isFifthToLastNote(cantusFirmusLength: Int, currentNotePositionInCantusFirmus: Int) = {
//    currentNotePositionInCantusFirmus == cantusFirmusLength - 4
//  }
//
//  private def isLeadingTone(tonicIdx: Int, noteIdx: Int, isTonicLowerHalf: Boolean): Boolean = {
//    val isUnalteredLeadingTone = isDirectLeadingTone(tonicIdx, noteIdx)
//    if (isTonicLowerHalf) {
//      isUnalteredLeadingTone || isDirectLeadingTone((tonicIdx + 7), noteIdx)
//    } else {
//      isUnalteredLeadingTone || isDirectLeadingTone((tonicIdx - 7), noteIdx)
//    }
//  }
//
//  private def isDirectLeadingTone(tonicIdx: Int, noteIdx: Int) = {
//    tonicIdx - noteIdx == 1
//  }
//
//  private def generatePenultimateNote(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String], previousNote: String): String = {
//    val two = inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) + 1)
//    val leadingTone = inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) - 1)
//
//    val twoDistance = math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(two) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote))
//    val leadingToneDistance = math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(leadingTone) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(previousNote))
//    val twoIsALeap = twoDistance > 4
//    val leadingToneIsALeap = leadingToneDistance > 4
//
//    if (!twoIsALeap && leadingToneIsALeap) {
//      two
//    } else if (twoIsALeap && !leadingToneIsALeap) {
//      leadingTone
//    } else if (twoIsALeap && leadingToneIsALeap) {
//      if (leadingToneDistance > twoDistance) {
//        leadingTone
//      } else {
//        two
//      }
//    } else {
//      if (randomService.nextDouble() >= 0.7) {
//        leadingTone
//      } else {
//        two
//      }
//    }
//  }
//
//  private def isPenultimateNote(length: Int, i: Int) = {
//    i == length - 1
//  }
//
//  private def isLastNote(length: Int, i: Int) = {
//    i == length
//  }
//
//  private def isFirstNote(i: Int) = {
//    i == 1
//  }
//}
