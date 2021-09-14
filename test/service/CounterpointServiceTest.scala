package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES}
import service.CounterpointServiceTest.{MAJOR_KEY_NOTES, TRIES}

import scala.util.{Failure, Random, Success}

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  def counterpointService = new CounterpointService(randomService)

  private def setUp(tonic: Int, maxTonic: Int) = {
    val length = Random.between(8, 17)
    (randomService.between _).expects(8, 17).returning(length)
    (randomService.between _).expects(3, maxTonic).returning(tonic)
    (randomService.nextInt _).expects(13).returning(Random.nextInt(13)).anyNumberOfTimes()
    (randomService.nextInt _).expects(11).returning(Random.nextInt(11)).noMoreThanOnce()
    (randomService.nextInt _).expects(12).returning(Random.nextInt(12)).noMoreThanOnce()
    (randomService.nextDouble _).expects().returning(Random.nextDouble()).noMoreThanOnce()
  }

  "Counterpoint service" should {
    "should generate a cantus firmus that starts and ends with the tonic" in {
      (1 to TRIES).map(_ => {
        // given
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        // when
        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            // then
            val head = cantusFirmus.head
            val last = cantusFirmus.last
            head mustBe last
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure the final note is approached by stepwise motion" in {
      (1 to TRIES).map(_ => {
        // given
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        // when
        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            // then
            List(AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1), AVAILABLE_CANTUS_FIRMUS_NOTES(tonic + 2)).contains(cantusFirmus(cantusFirmus.length - 2)) mustBe true
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should always approach the final note by the leading tone if the third to last note is the 2" in {
      (1 to TRIES).map(_ => {
        // given
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)

        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            // when
            if (cantusFirmus(cantusFirmus.length - 3) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic + 2)) {
              // then
              cantusFirmus(cantusFirmus.length - 2) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1)
            }
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure that no note is followed by the same note" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            cantusFirmus.zipWithIndex.map {
              case (note, i) =>
                if (i > 0) {
                  note must not equal cantusFirmus(i - 1)
                }
            }
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure that all notes are in the right major key" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        val notesInKey = MAJOR_KEY_NOTES
          .filter(key =>
            key.head.filterNot(c => c.isDigit) ==
              AVAILABLE_CANTUS_FIRMUS_NOTES(tonic).filterNot(c => c.isDigit)
          ).head
        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            cantusFirmus.count(note => notesInKey.contains(note)) mustBe cantusFirmus.length
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure that the third to last note is not the leading tone" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note =>
                note.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1).filterNot(c => c.isDigit)
              ).contains(cantusFirmus(cantusFirmus.length - 3)) mustBe false
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure that the leading tone always leads to the tonic" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            cantusFirmus.zipWithIndex.map {
              case (note, i) =>
                if (AVAILABLE_CANTUS_FIRMUS_NOTES
                  .filter(note =>
                    note.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1).filterNot(c => c.isDigit)
                  ).contains(note)) {
                  cantusFirmus(i + 1).filterNot(c => c.isDigit) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(tonic).filterNot(c => c.isDigit)
                }
            }
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
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

object CounterpointServiceTest {
  val MAJOR_KEY_NOTES = Set(
    List("E2", "F#/Gb2", "G#/Ab2", "A2", "B2", "C#/Db3", "D#/Eb3", "E3", "F#/Gb3", "G#/Ab3", "A3", "B3", "C#/Db4", "D#/Eb4"),
    List("F2", "G2", "A2", "A#/Bb2", "C3", "D3", "E3", "F3", "G3", "A3", "A#/Bb3", "C4", "D4", "E2"),
    List("F#/Gb2", "G#/Ab2", "A#/Bb2", "B2", "C#/Db3", "D#/Eb3", "F3", "F#/Gb3", "G#/Ab3", "A#/Bb3", "B3", "C#/Db4", "D#/Eb4", "F2"),
    List("G2", "A2", "B2", "C3", "D3", "E3", "F#/Gb3", "G3", "A3", "B3", "C4", "D4", "E2", "F#/Gb2"),
    List("G#/Ab2", "A#/Bb2", "C3", "C#/Db3", "D#/Eb3", "F3", "G3", "G#/Ab3", "A#/Bb3", "C4", "C#/Db4", "D#/Eb4", "F2", "G2"),
    List("A2", "B2", "C#/Db3", "D3", "E3", "F#/Gb3", "G#/Ab3", "A3", "B3", "C#/Db4", "D4", "E2", "F#/Gb2", "G#/Ab2"),
    List("A#/Bb2", "C3", "D3", "D#/Eb3", "F3", "G3", "A3", "A#/Bb3", "C4", "D4", "D#/Eb4", "F2", "G2", "A2"),
    List("B2", "C#/Db3", "D#/Eb3", "E3", "F#/Gb3", "G#/Ab3", "A#/Bb3", "B3", "C#/Db4", "D#/Eb4", "E2", "F#/Gb2", "G#/Ab2", "A#/Bb2"),
    List("C3", "D3", "E3", "F3", "G3", "A3", "B3", "C4", "D4", "E2", "F2", "G2", "A2", "B2"),
    List("C#/Db3", "D#/Eb3", "F3", "F#/Gb3", "G#/Ab3", "A#/Bb3", "C4", "C#/Db4", "D#/Eb4", "F2", "F#/Gb2", "G#/Ab2", "A#/Bb2", "C3"),
    List("D3", "E3", "F#/Gb3", "G3", "A3", "B3", "C#/Db4", "D4", "E2", "F#/Gb2", "G2", "A2", "B2", "C#/Db3"),
    List("D#/Eb3", "F3", "G3", "G#/Ab3", "A#/Bb3", "C4", "D4", "D#/Eb4", "F2", "G2", "G#/Ab2", "A#/Bb2", "C3", "D3"),
  )

  val TRIES = 100
}
