package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, MELODIC_CONSONANCES}
import service.CounterpointServiceFuzzingTest.{MAJOR_KEY_NOTES, TRIES}

import scala.util.{Failure, Random, Success}

class CounterpointServiceFuzzingTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]

  def counterpointRecursiveService = new CounterpointRecursiveService(randomService)

  private def setUp(tonic: Int, maxTonic: Int) = {
    val length = Random.between(8, 17)
    (randomService.between _).expects(8, 17).returning(length)
    (randomService.between _).expects(3, maxTonic).returning(tonic)
    (1 to 13).map(i =>
      (randomService.nextInt _).expects(i).returning(Random.nextInt(i)).anyNumberOfTimes()
    )
    (randomService.nextDouble _).expects().returning(Random.nextDouble()).noMoreThanOnce()
  }

  "Counterpoint service" should {
    "should generate a cantus firmus that starts and ends with the tonic" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            val head = cantusFirmus.head
            val last = cantusFirmus.last
            if (head != last) {
              println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
              println(cantusFirmus.toString())
            }
            head mustBe last
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure the final note is approached by stepwise motion" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            if (!List(AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1), AVAILABLE_CANTUS_FIRMUS_NOTES(tonic + 2)).contains(cantusFirmus(cantusFirmus.length - 2))) {
              println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
              println(cantusFirmus.toString())
            }
            List(AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1), AVAILABLE_CANTUS_FIRMUS_NOTES(tonic + 2)).contains(cantusFirmus(cantusFirmus.length - 2)) mustBe true
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should always approach the final note by the leading tone if the third to last note is the 2" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)

        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            if (cantusFirmus(cantusFirmus.length - 3) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic + 2)) {
              if (cantusFirmus(cantusFirmus.length - 2) != AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1)) {
                println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
                println(cantusFirmus.toString())
              }
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
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            cantusFirmus.zipWithIndex.map {
              case (note, i) =>
                if (i > 0) {
                  if (note == cantusFirmus(i - 1)) {
                    println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
                    println(cantusFirmus.toString())
                  }
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
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            if (cantusFirmus.count(note => notesInKey.contains(note)) != cantusFirmus.length) {
              println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
              println(cantusFirmus.toString())
            }
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
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            if (AVAILABLE_CANTUS_FIRMUS_NOTES
              .filter(note =>
                note.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1).filterNot(c => c.isDigit)
              ).contains(cantusFirmus(cantusFirmus.length - 3))) {
              println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
              println(cantusFirmus.toString())
            }
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
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            cantusFirmus.zipWithIndex.map {
              case (note, i) =>
                if (AVAILABLE_CANTUS_FIRMUS_NOTES
                  .filter(note =>
                    note.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1).filterNot(c => c.isDigit)
                  ).contains(note)) {
                  if (cantusFirmus(i + 1) != AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)) {
                    println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
                    println(cantusFirmus.toString())
                  }
                  cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
                }
            }
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure that all note-to-note progressions are melodic consonances and ensure that no leaps are greater than an octave" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            cantusFirmus.zipWithIndex.map {
              case (note, i) =>
                if (i > 0) {
                  if (!MELODIC_CONSONANCES.contains(
                    math.abs(
                      AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) -
                        AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i - 1))
                    )
                  )) {
                    println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
                    println(cantusFirmus.toString())
                  }
                  MELODIC_CONSONANCES.contains(
                    math.abs(
                      AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) -
                        AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i - 1))
                    )
                  ) mustBe true
                }
            }
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

    "should ensure that the range between the lowest note and the highest note is no larger than a tenth" in {
      (1 to TRIES).map(_ => {
        // says it passes but I think it's lying
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            val stepwiseValues = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note))
            if (stepwiseValues.max - stepwiseValues.min > 16) {
              println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
              println(cantusFirmus.toString())
            }
            stepwiseValues.max - stepwiseValues.min <= 16 mustBe true
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }

//    "should ensure that there are no more than two leaps larger than a 4th" in {
//      (1 to TRIES).map(_ => {
//        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
//        val tonic = Random.between(3, maxTonic)
//        setUp(tonic, maxTonic)
//        counterpointRecursiveService.generateCantusFirmus() match {
//          case Success(cantusFirmus) =>
//            val numLeaps = cantusFirmus.zipWithIndex.map {
//              case (note, i) =>
//                if (i > 0) {
//                  if (math.abs(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) - AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i - 1))) > 5) {
//                    note
//                  } else {
//                    ""
//                  }
//                } else {
//                  ""
//                }
//            }.count(note => note != "")
//            if (numLeaps > 2) {
//              println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
//              println(cantusFirmus.toString())
//            }
//            numLeaps <= 2 mustBe true
//          case Failure(e) =>
//            e.printStackTrace()
//            fail()
//        }
//      })
//    }

    "should ensure that each leap greater than a 3rd is " +
      "followed by a stepwise motion in the opposite direction" in {
      (1 to TRIES).map(_ => {
        val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
        val tonic = Random.between(3, maxTonic)
        setUp(tonic, maxTonic)
        counterpointRecursiveService.generateCantusFirmus() match {
          case Success(cantusFirmus) =>
            println(cantusFirmus)
            cantusFirmus.zipWithIndex.map {
              case (note, i) =>
                if (i > 0 && i < cantusFirmus.length - 1) {
                  val noteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note)
                  val prevNoteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i - 1))
                  val nextNoteIdx = AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(cantusFirmus(i + 1))
                  if (math.abs(noteIdx - prevNoteIdx) > 4) {
                    if (math.abs(nextNoteIdx - noteIdx) > 2) {
                      println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
                      println(cantusFirmus.toString())
                    }
                    math.abs(nextNoteIdx - noteIdx) <= 2 mustBe true
                  }
                }
            }
          case Failure(e) =>
            e.printStackTrace()
            fail()
        }
      })
    }
  }
}

object CounterpointServiceFuzzingTest {
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

  val TRIES = 1000
}
