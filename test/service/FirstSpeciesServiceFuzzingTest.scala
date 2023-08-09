package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CantusFirmusService.AVAILABLE_CANTUS_FIRMUS_NOTES
import service.CounterpointService.{GET_ALL_NOTES_BETWEEN_TWO_NOTES, HARMONIC_CONSONANCES, MELODIC_CONSONANCES}
import service.FirstSpeciesService.AVAILABLE_FIRST_SPECIES_NOTES
import service.FirstSpeciesServiceFuzzingTest.TRIES

import scala.util.{Failure, Random, Success}

class FirstSpeciesServiceFuzzingTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  val counterpointService = new CounterpointService()

  def cantusFirmusService = new CantusFirmusService(randomService, counterpointService)

  def firstSpeciesService = new FirstSpeciesService(randomService, counterpointService)

  private def setUpCantusFirmus(tonic: Int, maxTonic: Int) = {
    val length = Random.between(8, 17)
    (randomService.between _).expects(8, 17).returning(length)
    (randomService.between _).expects(3, maxTonic).returning(tonic)
    (1 to 13).map(i => {
      val next = Random.nextInt(i)
      (randomService.nextInt _).expects(i).returning(next).anyNumberOfTimes()
    })
    (randomService.nextDouble _).expects().returning(Random.nextDouble()).anyNumberOfTimes()
  }

  private def setUpFirstSpecies() = {
    val next = Random.nextInt(14)
    (randomService.nextInt _).expects(14).returning(next).anyNumberOfTimes()
  }

  private def testWrapper(callback: (List[String], List[String]) => Unit) = {
    (1 to TRIES).map(_ => {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      val tonic = Random.between(3, maxTonic)
      setUpCantusFirmus(tonic, maxTonic)
      setUpFirstSpecies()
      cantusFirmusService.generate() match {
        case Success(cantusFirmus) =>
          firstSpeciesService.generate(cantusFirmus) match {
            case Success(firstSpecies) =>
              callback(cantusFirmus, firstSpecies)
            case Failure(e) =>
              e.printStackTrace()
              fail()
          }
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    })
  }

  "First species service" should {
    "should generate a first species that's the same length as the cantus firmus" in {
      testWrapper((cantusFirmus, firstSpecies) => {
        if (firstSpecies.length != cantusFirmus.length) {
          failTest(firstSpecies)
        }
      })
    }

    "should ensure that all notes present in the first species are within the available notes" in {
      testWrapper((_, firstSpecies) => {
        firstSpecies.foreach(note => {
          if (!AVAILABLE_FIRST_SPECIES_NOTES.contains(note)) {
            failTest(firstSpecies)
          }
        })
      })
    }

    "should generate a first species that starts with either do or sol" in {
      testWrapper((cantusFirmus, firstSpecies) => {
        if (!List(0, 7, 12).contains(counterpointService.getInterval(cantusFirmus.head, firstSpecies.head, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4")))) {
          failTest(firstSpecies)
        }
      })
    }

    "should ensure that all notes are in the right major key" in {
      testWrapper((cantusFirmus, firstSpecies) => {
        val notesInKey = counterpointService.getInMajorKeyNotes(cantusFirmus.head, AVAILABLE_FIRST_SPECIES_NOTES)
        if (firstSpecies.count(note => notesInKey.contains(note)) != firstSpecies.length) {
          failTest(firstSpecies)
        }
      })
    }

    "should ensure that all note-to-note progressions are melodic consonances" in {
      testWrapper((_, firstSpecies) => {
        firstSpecies.zipWithIndex.map {
          case (note, i) =>
            if (i > 0) {
              if (!MELODIC_CONSONANCES.contains(
                math.abs(
                  AVAILABLE_FIRST_SPECIES_NOTES.indexOf(note) -
                    AVAILABLE_FIRST_SPECIES_NOTES.indexOf(firstSpecies(i - 1))
                )
              )) {
                failTest(firstSpecies)
              }
            }
        }
      })
    }

    "should ensure that all vertical intervals are harmonic consonances" in {
      testWrapper((cantusFirmus, firstSpecies) => {
        firstSpecies.zipWithIndex.map {
          case (note, i) =>
            if (!HARMONIC_CONSONANCES.contains(
              math.abs(
                GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4").indexOf(note) -
                  GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4").indexOf(cantusFirmus(i))
              )
            )) {
              println(note)
              println(i)
              failTest(firstSpecies, cantusFirmus)
            }
        }
      })
    }

    "should ensure that the first species ends with do" in {
      testWrapper((cantusFirmus, firstSpecies) => {
        if (!List(0, 12).contains(counterpointService.getInterval(cantusFirmus.last, firstSpecies.last, GET_ALL_NOTES_BETWEEN_TWO_NOTES("E2", "A4")))) {
          failTest(firstSpecies)
        }
      })
    }
  }

  private def failTest(firstSpecies: List[String], cantusFirmus: List[String] = List()) = {
    println("FAILURE FOUND WITH THIS FIRST SPECIES:")
    println(firstSpecies.toString())
    if (cantusFirmus.nonEmpty) {
      println("AGAINST THIS CANTUS FIRMUS:")
      println(cantusFirmus.toString())
    }
    fail()
  }
}

object FirstSpeciesServiceFuzzingTest {
  val TEST_CANTUS_FIRMUS = List("G2", "E2", "E3", "D3", "F #/ Gb3", "G3", "G2", "A2", "F #/ Gb2", "G2", "F#/Gb2", "G2")
  val TRIES = 100
}
