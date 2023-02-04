package service

import scala.util.{Failure, Success, Try}

class FirstSpeciesService(var counterpointService: CounterpointService) extends Counterpoint {
  def this() = {
    this(new CounterpointService())
  }

  override def generate(cantusFirmus: List[String]): Try[List[String]] = Success(cantusFirmus)

  override def format(firstSpecies: List[String]): List[String] = {
    firstSpecies.map(note => {
      val tonic = firstSpecies.head.dropRight(1)
      if (counterpointService.isSecondaryNoteAndTonicOfSharpKey(note, tonic)) {
        counterpointService.formatSharpKeySecondaryNote(note)
      } else if (counterpointService.isSecondaryNoteAndTonicOfFlatKey(note, tonic)) {
        counterpointService.formatFlatKeySecondaryNote(note)
      } else {
        counterpointService.formatPrimaryNote(note)
      }
    })
  }

  def convertNoteToUpperCase(note: String): String = {
    note.split("/").map(subNote => subNote.charAt(0).toUpper.toString.concat(subNote.takeRight(subNote.length - 1))).mkString("/")
  }
}
