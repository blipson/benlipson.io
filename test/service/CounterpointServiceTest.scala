package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES}

import scala.util.{Failure, Success}

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  val counterpointService = new CounterpointRecursiveService(randomService)

  "Counterpoint service" should {
    "should 3" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(10)
      val tonic = 16
      (randomService.between _).expects(3, maxTonic).returning(tonic)
      (randomService.nextInt _).expects(5).returning(4).anyNumberOfTimes()
      (randomService.nextInt _).expects(6).returning(0).anyNumberOfTimes()
      (randomService.nextInt _).expects(7).returning(5).anyNumberOfTimes()
      (randomService.nextInt _).expects(8).returning(5).anyNumberOfTimes()
      (randomService.nextInt _).expects(9).returning(7).anyNumberOfTimes()
      (randomService.nextInt _).expects(10).returning(9).anyNumberOfTimes()
      (randomService.nextInt _).expects(11).returning(3).anyNumberOfTimes()
      (randomService.nextInt _).expects(12).returning(9).anyNumberOfTimes()
      (randomService.nextInt _).expects(13).returning(5).anyNumberOfTimes()

      (randomService.nextInt _).expects(2).returning(1).anyNumberOfTimes()
      (randomService.nextInt _).expects(3).returning(0).anyNumberOfTimes()
      (randomService.nextInt _).expects(4).returning(1).anyNumberOfTimes()


      (randomService.nextInt _).expects(1).returning(0).anyNumberOfTimes()

      val notesInKey = counterpointService.getInMajorKeyCantusFirmusNotes(AVAILABLE_CANTUS_FIRMUS_NOTES(tonic))
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          val highestNote = cantusFirmus.maxBy(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note))
          if (cantusFirmus.count(note => note == highestNote) != 1 || !(cantusFirmus.indexOf(highestNote) >= cantusFirmus.length / 4) || !(cantusFirmus.indexOf(highestNote) <= cantusFirmus.length - (cantusFirmus.length / 4))) {
            println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
            println(cantusFirmus.toString())
          }
          cantusFirmus.count(note => note == highestNote) mustBe 1
          cantusFirmus.indexOf(highestNote) >= cantusFirmus.length / 4 mustBe true
          cantusFirmus.indexOf(highestNote) <= cantusFirmus.length - (cantusFirmus.length / 4) mustBe true
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should construct a new service" in {
      val counterpointService = new CounterpointRecursiveService()
    }

    "should generate available cantus firmus notes" in {
      GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(3, 2) mustBe
        List(
          "E2",
          "F2",
          "F#/Gb2",
          "G2",
          "G#/Ab2",
          "A2",
          "A#/Bb2",
          "B2",
          "C3",
          "C#/Db3",
          "D3",
          "D#/Eb3",
          "E3",
          "F3",
          "F#/Gb3",
          "G3",
          "G#/Ab3",
          "A3",
          "A#/Bb3",
          "B3",
          "C4",
          "C#/Db4",
          "D4",
          "D#/Eb4",
          "E4",
          "F4",
          "F#/Gb4",
          "G4",
          "G#/Ab4",
          "A4",
          "A#/Bb4",
          "B4",
          "C5",
          "C#/Db5",
          "D5",
          "D#/Eb5"
        )
    }
  }
}
