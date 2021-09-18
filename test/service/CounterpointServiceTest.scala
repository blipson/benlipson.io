package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES}

import scala.util.{Failure, Random, Success}

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  val counterpointService = new CounterpointService(randomService)

  "Counterpoint service" should {
    "should generate a cantus firmus that starts and ends with the tonic" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(12)
      (1 to 13).map(i =>
        (randomService.nextInt _).expects(i).returning(Random.nextInt(i)).anyNumberOfTimes()
      )
      (randomService.nextDouble _).expects().returning(Random.nextDouble()).noMoreThanOnce()
      // [E3, C#/Db4, A3, G#/Ab3, B2, F#/Gb2, C#/Db3, C#/Db4, E3, B3, F#/Gb3, E3]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          val head = cantusFirmus.head
          val last = cantusFirmus.last
          head mustBe "E3"
          last mustBe "E3"
          head mustBe last
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should approach the final note by the leading tone less often" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(12)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(4).returning(2)
      (randomService.nextDouble _).expects().returning(0.7)
      // E3, D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3, G#/Ab3, [D#/Eb3, E3]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          AVAILABLE_CANTUS_FIRMUS_NOTES(11) mustBe cantusFirmus(cantusFirmus.length - 2)
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should approach the final note by the 2 more often" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(12)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(4).returning(2)
      (randomService.nextDouble _).expects().returning(0.6)
      // E3, D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3, G#/Ab3, [F#/Gb3, E3]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          AVAILABLE_CANTUS_FIRMUS_NOTES(14) mustBe cantusFirmus(cantusFirmus.length - 2)
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should ensure that the leading tone always leads to the tonic when the tonic is E3" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(12)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(11).returning(5)
      (randomService.nextInt _).expects(4).returning(2)
      (randomService.nextDouble _).expects().returning(0.6)
      // E3, [D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3], G#/Ab3, F#/Gb3, E3
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.zipWithIndex.map {
            case (note, i) =>
              if (AVAILABLE_CANTUS_FIRMUS_NOTES
                .filter(note => note == "D#/Eb3"
                ).contains(note)) {
                cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
              }
          }
          cantusFirmus.count(note =>
            AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note => note == "D#/Eb3"
              ).contains(note)
          ) > 0 mustBe true

        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }


    "should ensure that the leading tone always leads to the tonic when the tonic is" +
      " in the lower half and the leading tone is in the upper half" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(3)
      (randomService.nextInt _).expects(8).returning(5)
      (randomService.nextInt _).expects(9).returning(6)
      (randomService.nextInt _).expects(7).returning(6)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(7).returning(5)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(1).returning(0)
      (randomService.nextDouble _).expects().returning(0.6)
      // [G2, D3, [F#/Gb3, G3], A3, G3, [F#/Gb3, G3], A3, D3, A2, G2]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.zipWithIndex.map {
            case (note, i) =>
              if (AVAILABLE_CANTUS_FIRMUS_NOTES
                .filter(note => note == "F#/Gb3"
                ).contains(note)) {
                cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
              }
          }
          cantusFirmus.count(note =>
            AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note => note == "F#/Gb3"
              ).contains(note)
          ) > 0 mustBe true
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should ensure that the leading tone always leads to the tonic when the tonic and " +
      "the leading tone are both in the lower half" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(3)
      (randomService.nextInt _).expects(8).returning(1)
      (randomService.nextInt _).expects(8).returning(4)
      (randomService.nextInt _).expects(7).returning(6)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(3).returning(0)
      (randomService.nextDouble _).expects().returning(0.6)
      // [G2, [F#/Gb2, G2], C3, G3, F#/Gb3, G3, F#/Gb3, G3, G2, A2, G2]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.zipWithIndex.map {
            case (note, i) =>
              if (AVAILABLE_CANTUS_FIRMUS_NOTES
                .filter(note => note == "F#/Gb2"
                ).contains(note)) {
                cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
              }
          }
          cantusFirmus.count(note =>
            AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note => note == "F#/Gb2"
              ).contains(note)
          ) > 0 mustBe true
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should ensure that the leading tone always leads to the tonic when the tonic and " +
      "the leading tone are both in the upper half" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(maxTonic - 2)
      (randomService.nextInt _).expects(10).returning(5)
      (randomService.nextInt _).expects(10).returning(5)
      (randomService.nextInt _).expects(10).returning(5)
      (randomService.nextInt _).expects(10).returning(5)
      (randomService.nextInt _).expects(4).returning(0)
      (randomService.nextDouble _).expects().returning(0.6)
      // [G3, F#/Gb3, G3, [F#/Gb3, G3, F#/Gb3, G3, F#/Gb3, G3], D3, A3, G3]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.zipWithIndex.map {
            case (note, i) =>
              if (AVAILABLE_CANTUS_FIRMUS_NOTES
                .filter(note => note == "F#/Gb3"
                ).contains(note)) {
                cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
              }
          }
          cantusFirmus.count(note =>
            AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note => note == "F#/Gb3"
              ).contains(note)
          ) > 0 mustBe true
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should ensure that the leading tone always leads to the tonic when the tonic is " +
      "in the upper half and the leading tone is in the lower half" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(maxTonic - 2)
      (randomService.nextInt _).expects(10).returning(0)
      (randomService.nextInt _).expects(7).returning(0)
      (randomService.nextInt _).expects(7).returning(5)
      (randomService.nextInt _).expects(7).returning(5)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(2).returning(0)
      (randomService.nextDouble _).expects().returning(0.6)
      // [G3, G2, [F#/Gb2, G2], E3, F#/Gb3, G3, F#/Gb3, G3, D3, A3, G3]
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          cantusFirmus.zipWithIndex.map {
            case (note, i) =>
              if (AVAILABLE_CANTUS_FIRMUS_NOTES
                .filter(note => note == "F#/Gb2"
                ).contains(note)) {
                cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
              }
          }
          cantusFirmus.count(note =>
            AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note => note == "F#/Gb2"
              ).contains(note)
          ) > 0 mustBe true
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should ensure that the highest cantus firmus note is unavailable when the tonic is " +
      "one half step above, as it would then be a leading tone" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(12)
      (randomService.nextInt _).expects(11).returning(10)
      // this line would expect (7) instead of (6) if the highest note were available
      (randomService.nextInt _).expects(6).returning(1)
      (randomService.nextInt _).expects(8).returning(5)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(7).returning(0)
      (randomService.nextInt _).expects(6).returning(0)
      (randomService.nextInt _).expects(6).returning(0)
      (randomService.nextInt _).expects(4).returning(0)
      (randomService.nextDouble _).expects().returning(0.6)
      // E3, C#/Db4, [E3], D#/Eb3, E3, D#/Eb3, E3, D#/Eb3, E3, B2, F#/Gb3, E3
      counterpointService.generateCantusFirmus()
    }

    "should not pick any notes larger than 14 half steps away during the middle " +
      "in order to leave room for the leading tone or the 2 at the end" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      val tonic = Random.between(3, maxTonic)
      (randomService.between _).expects(8, 17).returning(12)
      (randomService.between _).expects(3, maxTonic).returning(3)
      (randomService.nextInt _).expects(8).returning(7)
      // this line would expect (8) if a tenth (B3) were allowed
      (randomService.nextInt _).expects(7).returning(6)
      (randomService.nextInt _).expects(6).returning(4)
      (randomService.nextInt _).expects(7).returning(6)
      (randomService.nextInt _).expects(6).returning(4)
      (randomService.nextInt _).expects(6).returning(4)
      (randomService.nextInt _).expects(3).returning(1)
      (randomService.nextDouble _).expects().returning(0.7)
      // [G2, G3, B3, G3, B3, G3, B3, G3, B3, D3, [A2], G2]
      // is what it would be if it were in err
      counterpointService.generateCantusFirmus() match {
        case Success(cantusFirmus) =>
          val stepwiseValues = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note))
          stepwiseValues.max - stepwiseValues.min <= 16 mustBe true
        case Failure(e) =>
          e.printStackTrace()
          fail()
      }
    }

    "should construct a new service" in {
      val counterpointService = new CounterpointService()
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
