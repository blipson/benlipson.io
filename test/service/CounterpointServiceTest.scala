package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES}

import scala.util.{Failure, Random, Success}

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  def counterpointService = new CounterpointService(randomService)
  "Counterpoint service" should {
    "should generate a cantus firmus of the correct length" in {
      (randomService.between _).expects(8, 17).returning(10)
      (randomService.between _).expects(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7).returning(1)
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) => cantusFirmus.length mustBe 10
        case Failure(e) => e.printStackTrace()
      }
    }

    "should generate a cantus firmus that starts and ends with the tonic" in {
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7).returning(6)
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) => cantusFirmus.head mustBe cantusFirmus.last mustBe "A2"
        case Failure(e) => e.printStackTrace()
      }
    }

    "should determine all the cantus firmus notes for a given key" in {
      counterpointService.getInMajorKeyCantusFirmusNotes("C3") mustBe
        List(
          "E2",
          "F2",
          "G2",
          "A2",
          "B2",
          "C3",
          "D3",
          "E3",
          "F3",
          "G3",
          "A3",
          "B3",
          "C4",
          "D4",
        )

      counterpointService.getInMajorKeyCantusFirmusNotes("A2") mustBe
        List(
          "E2",
          "F#/Gb2",
          "G#/Ab2",
          "A2",
          "B2",
          "C#/Db3",
          "D3",
          "E3",
          "F#/Gb3",
          "G#/Ab3",
          "A3",
          "B3",
          "C#/Db4",
          "D4",
        )

      counterpointService.getInMajorKeyCantusFirmusNotes("C#/Db4") mustBe
        List(
          "F2",
          "F#/Gb2",
          "G#/Ab2",
          "A#/Bb2",
          "C3",
          "C#/Db3",
          "D#/Eb3",
          "F3",
          "F#/Gb3",
          "G#/Ab3",
          "A#/Bb3",
          "C4",
          "C#/Db4",
          "D#/Eb4",
        )
    }

    "should generate a cantus firmus where the final note is approached by the leading tone" in {
      (randomService.between _).expects(8, 17).returning(13)
      (randomService.between _).expects(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7).returning(4)
      (randomService.nextInt _).expects(13).returning(11)
      (randomService.nextInt _).expects(13).returning(3)
      (randomService.nextInt _).expects(13).returning(9)
      (randomService.nextInt _).expects(13).returning(12)
      (randomService.nextInt _).expects(13).returning(8)
      (randomService.nextInt _).expects(13).returning(4)
      (randomService.nextInt _).expects(13).returning(10)
      (randomService.nextInt _).expects(13).returning(1)
      (randomService.nextInt _).expects(13).returning(7)
      (randomService.nextInt _).expects(13).returning(5)
      (randomService.nextDouble _).expects().returning(1.0)
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus(cantusFirmus.length - 2) mustBe "G2"
        case Failure(e) => e.printStackTrace()
      }
    }

    "should generate a cantus firmus where the final note is approached by the 2" in {
      (randomService.between _).expects(8, 17).returning(13)
      (randomService.between _).expects(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7).returning(4)
      (randomService.nextInt _).expects(13).returning(11)
      (randomService.nextInt _).expects(13).returning(3)
      (randomService.nextInt _).expects(13).returning(9)
      (randomService.nextInt _).expects(13).returning(12)
      (randomService.nextInt _).expects(13).returning(8)
      (randomService.nextInt _).expects(13).returning(4)
      (randomService.nextInt _).expects(13).returning(10)
      (randomService.nextInt _).expects(13).returning(1)
      (randomService.nextInt _).expects(13).returning(7)
      (randomService.nextInt _).expects(13).returning(5)
      (randomService.nextDouble _).expects().returning(0.5)
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus(cantusFirmus.length - 2) mustBe "A#/Bb2"
        case Failure(e) => e.printStackTrace()
      }
    }

    "should ensure that no note is followed by the same note" in {
      (randomService.between _).expects(8, 17).returning(8)
      (randomService.between _).expects(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7).returning(7)
      (randomService.nextInt _).expects(13).returning(2)
      (randomService.nextInt _).expects(13).returning(2)
      (randomService.nextInt _).expects(13).returning(2)
      (randomService.nextInt _).expects(13).returning(2)
      (randomService.nextInt _).expects(13).returning(2)
      (randomService.nextDouble _).expects().returning(0.5)
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.zipWithIndex.map {
            case(note, i) =>
              if (i > 0) {
                note must not equal cantusFirmus(i - 1)
              }
          }
        case Failure(e) => e.printStackTrace()
      }
    }

    "should ensure that all notes are in the right major key" in {
      (randomService.between _).expects(8, 17).returning(11)
      (randomService.between _).expects(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7).returning(11)
      (randomService.nextInt _).expects(13).returning(3)
      (randomService.nextInt _).expects(13).returning(8)
      (randomService.nextInt _).expects(13).returning(7)
      (randomService.nextInt _).expects(13).returning(10)
      (randomService.nextInt _).expects(13).returning(11)
      (randomService.nextInt _).expects(13).returning(1)
      (randomService.nextInt _).expects(13).returning(6)
      (randomService.nextInt _).expects(13).returning(12)
      (randomService.nextDouble _).expects().returning(0.5)
      val notesInKey = counterpointService.getInMajorKeyCantusFirmusNotes(AVAILABLE_CANTUS_FIRMUS_NOTES(11))
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.count(note => notesInKey.contains(note)) mustBe cantusFirmus.length
        case Failure(e) => e.printStackTrace()
      }
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
