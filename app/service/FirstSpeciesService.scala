package service

import scala.util.{Failure, Success, Try}

class FirstSpeciesService(var counterpointService: CounterpointService) extends Counterpoint {
  def this() = {
    this(new CounterpointService())
  }

  override def generate(cantusFirmus: List[String]): Try[List[String]] = Success(cantusFirmus)

  override def formatOutput(firstSpecies: List[String]): List[String] = {
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

  def formatInput(cantusFirmus: List[String]): List[String] = {
    cantusFirmus.map(note => {
      note.split("/").map(subNote => {
        val upperNoteName = subNote.charAt(0).toUpper.toString
        val sharpOrFlatSymbol = subNote.takeRight(subNote.length - 1)
        if (sharpOrFlatSymbol.nonEmpty) {
          counterpointService.getCorrespondingFullNote(upperNoteName.concat(sharpOrFlatSymbol))
        } else {
          upperNoteName.concat(sharpOrFlatSymbol)
        }
      }).mkString("")
    })
  }
}
