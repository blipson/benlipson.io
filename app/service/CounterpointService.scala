package service

import service.CounterpointService.{FLAT_KEYS, MAJOR_KEY_INTERVALS, NOTES, OCTAVE, SHARP_KEYS}

import scala.util.matching.Regex

class CounterpointService {
  def getInterval(bottomNote: String, topNote: String, availableNotes: List[String]): Int =
    availableNotes.indexOf(topNote) - availableNotes.indexOf(bottomNote)

  def isFirstNote(line: List[String]): Boolean = line.isEmpty

  def isLastNote(length: Int, line: List[String]): Boolean = line.length == length - 1


  def formatOutput(line: List[String]): List[String] = {
    line.map(note => {
      val tonic = line.head.dropRight(1)
      if (isSecondaryNoteAndTonicOfSharpKey(note, tonic)) {
        formatSharpKeySecondaryNote(note)
      } else if (isSecondaryNoteAndTonicOfFlatKey(note, tonic)) {
        formatFlatKeySecondaryNote(note)
      } else {
        formatPrimaryNote(note)
      }
    })
  }

  def formatInput(line: List[String]): List[String] = {
    line.map(note => {
      note.split("/").map(subNote => {
        val upperNoteName = subNote.charAt(0).toUpper.toString
        val sharpOrFlatSymbol = subNote.takeRight(subNote.length - 1)
        if (sharpOrFlatSymbol.nonEmpty) {
          getCorrespondingFullNote(upperNoteName.concat(sharpOrFlatSymbol))
        } else {
          upperNoteName.concat(sharpOrFlatSymbol)
        }
      }).mkString("")
    })
  }

  def getInMajorKeyNotes(tonic: String, availableNotes: List[String]): List[String] =
    availableNotes.filter(note => {
      noteIsInMajorKey(tonic, note, availableNotes.indices
        .filter(interval => intervalIsInMajorKey(interval)), availableNotes)
    })

  private def noteIsInMajorKey(tonic: String, note: String, majorKeyIntervals: Seq[Int], availableNotes: List[String]) =
    majorKeyIntervals
      .map(interval => {
        getNoteForInterval(tonic, interval, availableNotes)
      })
      .contains(note)

  private def getNoteForInterval(tonic: String, interval: Int, availableNotes: List[String]) = {
    val newTonic = if (availableNotes.indexOf(tonic) < 0) {
      val note = tonic.substring(0, tonic.length - 1)
      val octave = (tonic.substring(tonic.length - 1, tonic.length).toInt + 1).toString
      note + octave
    } else {
      tonic
    }
    availableNotes(
      (availableNotes.indexOf(newTonic) + interval) %
        (OCTAVE * 2)
    )
  }

  private def intervalIsInMajorKey(interval: Int) =
    MAJOR_KEY_INTERVALS.contains(interval % OCTAVE)

  private def isSecondaryNoteAndTonicOfSharpKey(note: String, key: String): Boolean = {
    isSecondaryNote(note) && isTonicOfSharpKey(key)
  }

  private def isTonicOfSharpKey(key: String) = {
    SHARP_KEYS.contains(key)
  }

  private def isSecondaryNote(note: String) = {
    note.contains("#")
  }

  private def formatSharpKeySecondaryNote(note: String): String = {
    note.split("/").head.toLowerCase + "/" + note.last
  }

  private def isSecondaryNoteAndTonicOfFlatKey(note: String, tonic: String): Boolean = {
    isSecondaryNote(note) && isTonicOfFlatKey(tonic)
  }

  private def isTonicOfFlatKey(tonic: String) = {
    if (isSecondaryNote(tonic)) {
      FLAT_KEYS.contains(tonic.split("/")(1))
    } else {
      FLAT_KEYS.contains(tonic)
    }
  }

  private def formatFlatKeySecondaryNote(note: String): String = {
    note.split("/")(1).toLowerCase.dropRight(1) + "/" + note.last
  }

  private def formatPrimaryNote(note: String): String = {
    note.toLowerCase.dropRight(1) + "/" + note.last
  }

  private def getCorrespondingFullNote(note: String): String = {
    NOTES.filter(fullNote => fullNote.contains(note)).head
  }
}

object CounterpointService {
  val SHARP_KEYS: Set[String] = Set("A", "B", "C", "D", "E", "G")
  val FLAT_KEYS: Set[String] = Set("F", "Bb", "Eb", "Ab", "Db", "Gb")
  val NOTES: List[String] = List(
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
  val MAJOR_KEY_INTERVALS: Set[Int] = Set(
    0, 2, 4, 5, 7, 9, 11
  )
  val OCTAVE = 12
  private val NOTE_MATCHER: Regex = """(.+)(\d+)""".r

  private def SEPARATE_NOTE_AND_OCTAVE(note: String): (String, Int) =
    note match {
      case NOTE_MATCHER(note, octave) => (note, octave.toInt)
    }

  def GET_ALL_NOTES_BETWEEN_TWO_NOTES(lowNote: String, highNote: String): List[String] = {
    val (lowNoteName, lowOctave) =
      SEPARATE_NOTE_AND_OCTAVE(
        lowNote
      )
    val (highNoteName, highOctave) =
      SEPARATE_NOTE_AND_OCTAVE(
        highNote
      )

    val firstNoteIsAtOrAboveC = NOTES.indexOf(lowNoteName) >= NOTES.indexOf("C")
    val (_, highOctaveWithCSplit) = if (firstNoteIsAtOrAboveC) {
      val (noteName, octave) = (highNote, highOctave)
      (noteName, octave + 1)
    } else {
      (highNote, highOctave)
    }
    (lowOctave to highOctaveWithCSplit).flatMap(octave => {
      val filteredNotes = if (octave == lowOctave) {
        NOTES.filter(note => NOTES.indexOf(note) >= NOTES.indexOf(lowNoteName))
      } else if (octave == highOctaveWithCSplit) {
        NOTES.filter(note => NOTES.indexOf(note) <= NOTES.indexOf(highNoteName))
      } else {
        NOTES
      }

      filteredNotes.map(note => {
        if (NOTES.indexOf(note) < NOTES.indexOf("C") && firstNoteIsAtOrAboveC) {
          note.concat((octave - 1).toString)
        } else if (NOTES.indexOf(note) < NOTES.indexOf("C") && !firstNoteIsAtOrAboveC) {
          note.concat(octave.toString)
        } else if (NOTES.indexOf(note) >= NOTES.indexOf("C") && firstNoteIsAtOrAboveC) {
          note.concat(octave.toString)
        } else if (NOTES.indexOf(note) >= NOTES.indexOf("C") && !firstNoteIsAtOrAboveC) {
          note.concat((octave + 1).toString)
        } else {
          // will never hit
          note.concat(octave.toString)
        }
      })
    }).toList
  }
}
