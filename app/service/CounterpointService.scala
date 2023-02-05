package service

import service.CounterpointService.{FLAT_KEYS, NOTES, SHARP_KEYS}

class CounterpointService {
  def isSecondaryNoteAndTonicOfSharpKey(note: String, key: String): Boolean = {
    isSecondaryNote(note) && isTonicOfSharpKey(key)
  }

  private def isTonicOfSharpKey(key: String) = {
    SHARP_KEYS.contains(key)
  }

  private def isSecondaryNote(note: String) = {
    note.contains("#")
  }

  def formatSharpKeySecondaryNote(note: String): String = {
    note.split("/").head.toLowerCase + "/" + note.last
  }

  def isSecondaryNoteAndTonicOfFlatKey(note: String, tonic: String): Boolean = {
    isSecondaryNote(note) && isTonicOfFlatKey(tonic)
  }

  private def isTonicOfFlatKey(tonic: String) = {
    if (isSecondaryNote(tonic)) {
      FLAT_KEYS.contains(tonic.split("/")(1))
    } else {
      FLAT_KEYS.contains(tonic)
    }
  }

  def formatFlatKeySecondaryNote(note: String): String = {
    note.split("/")(1).toLowerCase.dropRight(1) + "/" + note.last
  }

  def formatPrimaryNote(note: String): String = {
    note.toLowerCase.dropRight(1) + "/" + note.last
  }

  def getCorrespondingFullNote(note: String): String = {
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
}
