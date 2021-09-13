package service

import service.CounterpointService._

import scala.util.Try

class CounterpointService(var randomService: RandomService) {
  def this() = {
    this(new RandomService())
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


  def pickPenultimateNote(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String]): String = {
    if (randomService.nextDouble() >= 0.7) {
      inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) - 1)
    } else {
      inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) + 1)
    }
  }

  def generateCantusFirmus(): Try[List[String]] = Try {
    val length = randomService.between(MIN_LENGTH, MAX_LENGTH + 1)
    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(MIN_TONIC, MAX_TONIC))
    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
    (1 to length).foldLeft(List.empty[String]){(acc, i) => {
      if (isFirstNote(i) || isLastNote(length, i)) {
        acc :+ tonic
      } else {
        val lastNote = acc(i - 2)
        val tonicIdx = inMajorKeyCantusFirmusNotes.indexOf(tonic)
        if (isPenultimateNote(length, i)) {
          if (lastNote == inMajorKeyCantusFirmusNotes(tonicIdx + 1)) {
            acc :+ inMajorKeyCantusFirmusNotes(tonicIdx - 1)
          } else {
            acc :+ pickPenultimateNote(tonic, inMajorKeyCantusFirmusNotes)
          }
        } else {
          val lastNoteIdx = inMajorKeyCantusFirmusNotes.indexOf(lastNote)
          val halfOfInKeyNotesLen = inMajorKeyCantusFirmusNotes.length / 2
          val isTonicLowerHalf = tonicIdx < halfOfInKeyNotesLen
          val isLastNoteLowerHalf = lastNoteIdx < 7
          val isLastNoteLeadingTone: Boolean = isLeadingTone(
            tonicIdx,
            lastNoteIdx,
            isTonicLowerHalf
          )
          if (isLastNoteLeadingTone) {
            if ((isLastNoteLowerHalf && isTonicLowerHalf) || (!isLastNoteLowerHalf && !isTonicLowerHalf)) {
              acc :+ tonic
            } else if (isLastNoteLowerHalf && !isTonicLowerHalf) {
              acc :+ inMajorKeyCantusFirmusNotes(tonicIdx - halfOfInKeyNotesLen)
            } else {
              acc :+ inMajorKeyCantusFirmusNotes(tonicIdx + halfOfInKeyNotesLen)
            }
          } else {
            val lastNoteRemoved = inMajorKeyCantusFirmusNotes.filter(note => note != lastNote)
            if (isThirdToLastNote(length, i)) {
              val leadingTones = inMajorKeyCantusFirmusNotes.filter(note => {
                note.filterNot(c => c.isDigit) == inMajorKeyCantusFirmusNotes(tonicIdx - 1).filterNot(c => c.isDigit)
              })
              val leadingTonesRemoved = lastNoteRemoved.filterNot(note => leadingTones.contains(note))
              acc :+ leadingTonesRemoved(randomService.nextInt(leadingTonesRemoved.length))
            } else {
              acc :+ lastNoteRemoved(randomService.nextInt(lastNoteRemoved.length))
            }
          }
        }
      }
    }}
  }

  private def isThirdToLastNote(length: Int, i: Int) = {
    i == length - 2
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

  def GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(numOctaves: Int, startOctave: Int): Seq[String] =
    (0 until NOTES.length * numOctaves).map(noteIdx => {
      val stepsAboveC = OCTAVE - NOTES.slice(NOTES.length - NOTES.indexOf("C"), NOTES.length).length
      val currentOctave = startOctave + math.floor((noteIdx + stepsAboveC) / OCTAVE).toInt
      NOTES(noteIdx % NOTES.length).concat(currentOctave.toString)
    }).toList

  val AVAILABLE_CANTUS_FIRMUS_NOTES: Seq[String] = GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(2, 2)
  val MIN_TONIC: Int = 3
  val MAX_TONIC: Int = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
}
