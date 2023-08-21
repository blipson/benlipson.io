package service

import service.CounterpointService.GET_ALL_NOTES_BETWEEN_TWO_NOTES
import service.FirstSpeciesService.AVAILABLE_FIRST_SPECIES_NOTES

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

class FirstSpeciesService(var randomService: RandomService, var counterpointService: CounterpointService) extends Counterpoint {
  def this() = {
    this(new RandomService(), new CounterpointService())
  }

  override def generate(cantusFirmus: List[String]): Try[List[String]] = Try {
    val inMajorKeyFirstSpeciesNotes = counterpointService.getInMajorKeyNotes(cantusFirmus.head, AVAILABLE_FIRST_SPECIES_NOTES)
    val firstSpecies = generateFirstSpeciesRecursive(cantusFirmus, inMajorKeyFirstSpeciesNotes)
    return if (firstSpecies.nonEmpty) {
      Success(firstSpecies)
    } else {
      println("CF: " + cantusFirmus)
      Failure(new Exception("Could not generate first species."))
    }
  }

  @tailrec
  private def generateFirstSpeciesRecursive(cantusFirmus: List[String], inMajorKeyNotes: List[String], firstSpecies: List[String] = List(), invalidLines: List[List[String]] = List(), invalidNotePos: Int = -1): List[String] = {
    println(firstSpecies)
    if (firstSpecies.length == cantusFirmus.length) {
      firstSpecies
    } else {
      generateFirstSpeciesNote(cantusFirmus, inMajorKeyNotes, firstSpecies, invalidLines) match {
        case Success(nextNote) => {
          generateFirstSpeciesRecursive(cantusFirmus, inMajorKeyNotes, firstSpecies :+ nextNote, invalidLines, invalidNotePos)
        }
        case Failure(invalidNoteException) =>
          val invalidNoteMessage = invalidNoteException.getMessage
          if (invalidNoteMessage == "Can not generate first species.") {
            firstSpecies
          } else {
            generateFirstSpeciesRecursive(cantusFirmus, inMajorKeyNotes, firstSpecies.dropRight(1), invalidLines :+ invalidNoteMessage.split(" - ").last.filter(c => !"List()".contains(c)).replace(" ", "").split(",").toList, invalidNoteMessage.last.toInt)
          }
      }
    }
  }

  private def generateFirstSpeciesNote(cantusFirmus: List[String], inMajorKeyNotes: List[String], firstSpecies: List[String], invalidLines: List[List[String]]): Try[String] = {
    val isFirstNote = counterpointService.isFirstNote(firstSpecies)
    if (isFirstNote && invalidLines.exists(line => line.length == 1)) {
      Failure(new Exception("Can not generate first species."))
    } else {
      val universalRulesApplied = if (!isFirstNote) applyUniversalRules(inMajorKeyNotes, cantusFirmus, firstSpecies, invalidLines) else inMajorKeyNotes
      println("UNIVERSAL APPLIED: " + universalRulesApplied)
      val leapsRulesApplied =  counterpointService.applyLeapsRules(inMajorKeyNotes, AVAILABLE_FIRST_SPECIES_NOTES, firstSpecies, universalRulesApplied)
      println("LEAPS APPLIED: " + leapsRulesApplied)
      val availableNotes = applyIndividualRules(firstSpecies, leapsRulesApplied, cantusFirmus)

      println("AVAILABLE: " + availableNotes)
      if (availableNotes.isEmpty) {
        Failure(new Exception(s"$cantusFirmus - $firstSpecies"))
      } else {
        Success(availableNotes(randomService.nextInt(availableNotes.length)))
      }
    }
  }

  private def applyUniversalRules(inMajorKeyNotes: List[String], cantusFirmus: List[String], firstSpecies: List[String], invalidLines: List[List[String]]): List[String] = {
    inMajorKeyNotes.filter(note => {
      !invalidLines.contains(firstSpecies :+ note) &&
      AVAILABLE_FIRST_SPECIES_NOTES.contains(note) &&
      counterpointService.isMelodicConsonance(firstSpecies.last, note, AVAILABLE_FIRST_SPECIES_NOTES) &&
        counterpointService.isHarmonicConsonance(note, cantusFirmus(firstSpecies.length), GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))
    })
  }

  def applyIndividualRules(firstSpecies: List[String], notes: Seq[String], cantusFirmus: List[String]): Seq[String] = {
    val climaxMustBeInMiddleApplied = counterpointService.applyClimaxMustBeInMiddleRule(cantusFirmus, AVAILABLE_FIRST_SPECIES_NOTES, cantusFirmus.length, notes)

    if (counterpointService.isFirstNote(firstSpecies)) {
      // TODO: ADD 12 IN. IT'S NOT BUILT TO CHOOSE A DIFFERENT FIRST NOTE.
      notes.filter(note => List(0, 7).contains(counterpointService.getInterval(cantusFirmus.head, note, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))))
    } else if (counterpointService.isLastNote(cantusFirmus.length, firstSpecies)) {
      // todo: if 2nd to last cantus note is re,
      //       then List(12).contains()
      //       else if 2nd to last cantus note is ti,
      //       then List(0).contains()

      notes.filter(note => List(0, 12).contains(counterpointService.getInterval(cantusFirmus.last, note, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))))
    } else if (counterpointService.isLeadingTone(AVAILABLE_FIRST_SPECIES_NOTES, firstSpecies, cantusFirmus.head)) {
      notes.filter(note => counterpointService.applyLeadingToneLeadsToTonicRule(firstSpecies, note, AVAILABLE_FIRST_SPECIES_NOTES))
    } else {
      notes
    }
  }

  override def formatOutput(firstSpecies: List[String]): List[String] =
    counterpointService.formatOutput(firstSpecies)

  def formatInput(cantusFirmus: List[String]): List[String] =
    counterpointService.formatInput(cantusFirmus)
}

object FirstSpeciesService {
  val AVAILABLE_FIRST_SPECIES_NOTES: List[String] = GET_ALL_NOTES_BETWEEN_TWO_NOTES("A2", "A4")
}
