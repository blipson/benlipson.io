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
    println("\n")
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
      val leapsRulesApplied =  counterpointService.applyLeapsRules(inMajorKeyNotes, AVAILABLE_FIRST_SPECIES_NOTES, firstSpecies, universalRulesApplied)
      val availableNotes = applyIndividualRules(firstSpecies, leapsRulesApplied, cantusFirmus)

      if (availableNotes.isEmpty) {
        Failure(new Exception(s"$cantusFirmus - $firstSpecies"))
      } else {
        Success(availableNotes(randomService.nextInt(availableNotes.length)))
      }
    }
  }

  private def applyUniversalRules(inMajorKeyNotes: List[String], cantusFirmus: List[String], firstSpecies: List[String], invalidLines: List[List[String]]): List[String] = {
    val countsOfNotes = firstSpecies.groupBy(identity).view.mapValues(_.size).toSeq
    inMajorKeyNotes.filter(note => {
      val ret = counterpointService.applyNoRepeatedNotesRule(firstSpecies, note) &&
      !invalidLines.contains(firstSpecies :+ note) &&
      AVAILABLE_FIRST_SPECIES_NOTES.contains(note) &&
      counterpointService.isMelodicConsonance(firstSpecies.last, note, AVAILABLE_FIRST_SPECIES_NOTES) &&
        counterpointService.isHarmonicConsonance(note, cantusFirmus(firstSpecies.length), GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))
      counterpointService.applyMaxRepetitionRules(firstSpecies, countsOfNotes, note, ret, cantusFirmus.length)
    })
  }

  def applyIndividualRules(firstSpecies: List[String], notes: Seq[String], cantusFirmus: List[String]): Seq[String] = {
    // TODO: Figure out why this breaks it so bad. Hard coded 19?
    val climaxMustBeInMiddleApplied = counterpointService.applyClimaxMustBeInMiddleRule(cantusFirmus, AVAILABLE_FIRST_SPECIES_NOTES, cantusFirmus.length, notes)

    val notePairs =
      firstSpecies.zipWithIndex.map {
        case (note, i) =>
          if (i > 0) {
            (firstSpecies(i - 1), note)
          } else {
            ("", "")
          }
      }.filter(pair => pair._1 != "" && pair._2 != "")

    val noMotivesApplied = if (
      !counterpointService.isPenultimateNote(cantusFirmus.length, firstSpecies) &&
        !counterpointService.isLastNote(cantusFirmus.length, firstSpecies) &&
        !counterpointService.isFirstNote(firstSpecies)
    ) {
      notes.filter(note => !notePairs.contains((firstSpecies.last, note)))
    } else {
      notes
    }

    if (counterpointService.isFirstNote(firstSpecies)) {
      // TODO: ADD 12 IN. IT'S NOT BUILT TO CHOOSE A DIFFERENT FIRST NOTE.
      noMotivesApplied.filter(note => List(0, 7).contains(counterpointService.getInterval(cantusFirmus.head, note, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))))
    } else if (counterpointService.isLastNote(cantusFirmus.length, firstSpecies)) {
      // todo: if 2nd to last cantus note is re,
      //       then List(12).contains()
      //       else if 2nd to last cantus note is ti,
      //       then List(0).contains()

      noMotivesApplied.filter(note => List(0, 12).contains(counterpointService.getInterval(cantusFirmus.last, note, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))))
    } else if (counterpointService.isLeadingTone(AVAILABLE_FIRST_SPECIES_NOTES, firstSpecies, cantusFirmus.head)) {
      noMotivesApplied.filter(note => counterpointService.applyLeadingToneLeadsToTonicRule(firstSpecies, note, AVAILABLE_FIRST_SPECIES_NOTES))
    } else {
      noMotivesApplied
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
