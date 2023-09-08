package service

import service.CounterpointService.{GET_ALL_NOTES_BETWEEN_TWO_NOTES, PERFECT_INTERVALS}
import service.FirstSpeciesService.AVAILABLE_FIRST_SPECIES_NOTES

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

class FirstSpeciesService(var randomService: RandomService, var counterpointService: CounterpointService) extends Counterpoint {
  def this() = {
    this(new RandomService(), new CounterpointService())
  }

  override def generate(cantusFirmus: List[String]): Try[List[String]] = Try {
    println("Generating first species...")
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
    if (firstSpecies.length == cantusFirmus.length) {
      firstSpecies
    } else {
      generateFirstSpeciesNote(cantusFirmus, inMajorKeyNotes, firstSpecies, invalidLines) match {
        case Success(nextNote) =>
          generateFirstSpeciesRecursive(cantusFirmus, inMajorKeyNotes, firstSpecies :+ nextNote, invalidLines, invalidNotePos)
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
    if (isFirstNote) {
      if (invalidLines.count(line => line.length == 1) == 3) {
        println(invalidLines)
        Failure(new Exception("Can not generate first species."))
      } else {
        val availableNotes = inMajorKeyNotes.filter(note => {
          List(0, 7, 12).contains(counterpointService.getInterval(cantusFirmus.head, note, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))) &&
            !invalidLines.contains(firstSpecies :+ note)
        })
        if (availableNotes.isEmpty) {
          Failure(new Exception("Can not generate first species."))
        } else {
          Success(availableNotes(randomService.nextInt(availableNotes.length)))
        }
      }
    } else {
      val universalRulesApplied = applyUniversalRules(inMajorKeyNotes, cantusFirmus, firstSpecies, invalidLines)
      val leapsRulesApplied =  counterpointService.applyLeapsRules(inMajorKeyNotes, AVAILABLE_FIRST_SPECIES_NOTES, firstSpecies, universalRulesApplied)
      val availableNotes = applyIndividualRules(firstSpecies, leapsRulesApplied, cantusFirmus, inMajorKeyNotes)

      if (availableNotes.isEmpty) {
        Failure(new Exception(s"$cantusFirmus - $firstSpecies"))
      } else {
        Success(availableNotes(randomService.nextInt(availableNotes.length)))
      }
    }
  }


  private def isPerfectInterval(firstNote: String, secondNote: String, availableNotes: List[String]): Boolean = {
    val firstNoteIdx = availableNotes.indexOf(firstNote)
    val secondNoteIdx = availableNotes.indexOf(secondNote)
    firstNoteIdx != -1 && secondNoteIdx != -1 &&
      PERFECT_INTERVALS.contains(math.abs(availableNotes.indexOf(firstNote) - availableNotes.indexOf(secondNote)))
  }

  private def isPerfectFourth(firstNote: String, secondNote: String, availableNotes: List[String]): Boolean = {
    val firstNoteIdx = availableNotes.indexOf(firstNote)
    val secondNoteIdx = availableNotes.indexOf(secondNote)
    firstNoteIdx != -1 && secondNoteIdx != -1 && math.abs(firstNoteIdx - secondNoteIdx) == 5
  }

  private def isPerfectFifth(firstNote: String, secondNote: String, availableNotes: List[String]): Boolean = {
    val firstNoteIdx = availableNotes.indexOf(firstNote)
    val secondNoteIdx = availableNotes.indexOf(secondNote)
    firstNoteIdx != -1 && secondNoteIdx != -1 && math.abs(firstNoteIdx - secondNoteIdx) == 7
  }

  private def isPerfectOctave(firstNote: String, secondNote: String, availableNotes: List[String]): Boolean = {
    val firstNoteIdx = availableNotes.indexOf(firstNote)
    val secondNoteIdx = availableNotes.indexOf(secondNote)
    firstNoteIdx != -1 && secondNoteIdx != -1 && math.abs(firstNoteIdx - secondNoteIdx) == 12
  }

  private def isParallelPerfect(firstNote: String, secondNote: String, prevFirstNote: String, prevSecondNote: String, availableNotes: List[String]): Boolean = {
    if (isPerfectFourth(firstNote, secondNote, availableNotes)) {
      if (isPerfectFourth(prevFirstNote, prevSecondNote, availableNotes)) {
        false
      } else {
        true
      }
    } else if (isPerfectFifth(firstNote, secondNote, availableNotes)) {
      if (isPerfectFifth(prevFirstNote, prevSecondNote, availableNotes)) {
        false
      } else {
        true
      }
    } else if (isPerfectOctave(firstNote, secondNote, availableNotes)) {
      if (isPerfectOctave(prevFirstNote, prevSecondNote, availableNotes)) {
        false
      } else {
        true
      }
    } else {
      true
    }
  }

  private def applyUniversalRules(notes: List[String], cantusFirmus: List[String], firstSpecies: List[String], invalidLines: List[List[String]]): List[String] = {
    val countsOfNotes = firstSpecies.groupBy(identity).view.mapValues(_.size).toSeq
    notes.filter(note => {
      val isValid = counterpointService.applyNoRepeatedNotesRule(firstSpecies, note) &&
      !invalidLines.contains(firstSpecies :+ note) &&
      AVAILABLE_FIRST_SPECIES_NOTES.contains(note) &&
      counterpointService.isMelodicConsonance(firstSpecies.last, note, AVAILABLE_FIRST_SPECIES_NOTES) &&
        counterpointService.isHarmonicConsonance(note, cantusFirmus(firstSpecies.length), GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4")) &&
        !isParallelPerfect(note,
          cantusFirmus(firstSpecies.length),
          firstSpecies.last,
          cantusFirmus(firstSpecies.length - 1),
          GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))
//        (!isPerfectInterval(note, cantusFirmus(firstSpecies.length), GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4")) ||
//          (isPerfectInterval(note, cantusFirmus(firstSpecies.length), GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4")) &&
//          !isPerfectInterval(firstSpecies.last, cantusFirmus(firstSpecies.length - 1), GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))))
      counterpointService.applyMaxRepetitionRules(firstSpecies, countsOfNotes, note, isValid, cantusFirmus.length)
    })
  }

  def applyIndividualRules(firstSpecies: List[String], notes: Seq[String], cantusFirmus: List[String], inMajorKeyNotes: List[String]): Seq[String] = {
    // TODO: Figure out why this breaks it so bad. Hard coded 19?
//    val climaxMustBeInMiddleApplied = counterpointService.applyClimaxMustBeInMiddleRule(cantusFirmus, AVAILABLE_FIRST_SPECIES_NOTES, cantusFirmus.length, notes)

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

    if (counterpointService.isLastNote(cantusFirmus.length, firstSpecies)) {
      // todo: if 2nd to last cantus note is re,
      //       then List(12).contains()
      //       else if 2nd to last cantus note is ti,
      //       then List(0).contains()

      noMotivesApplied.filter(note => List(0, 12).contains(counterpointService.getInterval(cantusFirmus.last, note, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4"))))
    } else if (counterpointService.isPenultimateNote(cantusFirmus.length, firstSpecies)) {
      noMotivesApplied.filter(note => counterpointService.applyPenultimateStepwiseMotionRule(inMajorKeyNotes, cantusFirmus.head, cantusFirmus(firstSpecies.length - 1)).contains(note))
    } else if (counterpointService.isLeadingTone(AVAILABLE_FIRST_SPECIES_NOTES, firstSpecies, cantusFirmus.head)) {
      noMotivesApplied.filter(note => counterpointService.applyLeadingToneLeadsToTonicRule(firstSpecies, note, AVAILABLE_FIRST_SPECIES_NOTES))
    } else if (counterpointService.isAntePenultimateNote(cantusFirmus.length, firstSpecies)) {
      noMotivesApplied.filter(note => counterpointService.applyAntePenultimateCannotBeLeadingToneRule(inMajorKeyNotes, cantusFirmus.head, note))
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
