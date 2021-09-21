package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES}

import scala.util.{Failure, Success}

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  val counterpointRecursiveService = new CounterpointRecursiveService(randomService)

  "Counterpoint service" should {
//    "should go back and override invalid notes" in {
//      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
//      (randomService.between _).expects(8, 17).returning(15)
//      (randomService.between _).expects(3, maxTonic).returning(11)
//      (randomService.nextInt _).expects(11).returning(6)
//      (randomService.nextInt _).expects(11).returning(6)
//      (randomService.nextInt _).expects(8).returning(7)
//      (randomService.nextInt _).expects(6).returning(1)
//      (randomService.nextInt _).expects(11).returning(6)
//      (randomService.nextInt _).expects(8).returning(7)
//      (randomService.nextInt _).expects(6).returning(1)
//      (randomService.nextInt _).expects(11).returning(6)
//      (randomService.nextInt _).expects(8).returning(7)
//      (randomService.nextInt _).expects(6).returning(1)
//      (randomService.nextInt _).expects(11).returning(6)
//      (randomService.nextInt _).expects(8).returning(7)
//      (randomService.nextInt _).expects(7).returning(6)
//      (randomService.nextInt _).expects(1).returning(0)
//
//      // Len: 15
//      // List(D#/Eb3, G3, G#/Ab3, D#/Eb4, G3, G#/Ab3, D#/Eb4, G3, G#/Ab3, D#/Eb4, G3, G#/Ab3, D#/Eb4)
//      counterpointRecursiveService.generateCantusFirmus() match {
//        case Success(cantusFirmus) =>
//          val head = cantusFirmus.head
//          val last = cantusFirmus.last
//          if (head != last) {
//            println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
//            println(cantusFirmus.toString())
//          }
//          head mustBe last
//        case Failure(e) =>
//          e.printStackTrace()
//          fail()
//      }
//    }
//
//    "should go back multiple times on the same note if it finds multiple invalid notes" in {
//      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
//      (randomService.between _).expects(8, 17).returning(8)
//      (randomService.between _).expects(3, maxTonic).returning(4)
//      (randomService.nextInt _).expects(8).returning(7).anyNumberOfTimes()
//      (randomService.nextInt _).expects(10).returning(5).anyNumberOfTimes()
//      (randomService.nextInt _).expects(9).returning(8).anyNumberOfTimes()
//      (randomService.nextInt _).expects(6).returning(1).anyNumberOfTimes()
//      (randomService.nextInt _).expects(7).returning(6).anyNumberOfTimes()
//      (randomService.nextInt _).expects(1).returning(0).anyNumberOfTimes()
//
//      // Len: 8
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3, D#/Eb4)
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3)
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3, C4)
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3)
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3, A#/Bb3)
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3, A#/Bb3, A#/Bb2)
//      // List(G#/Ab2, G#/Ab3, G3, D#/Eb4, G3, A#/Bb3, A#/Bb2, G#/Ab2)
//      counterpointRecursiveService.generateCantusFirmus() match {
//        case Success(cantusFirmus) =>
//          val head = cantusFirmus.head
//          val last = cantusFirmus.last
//          if (head != last) {
//            println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
//            println(cantusFirmus.toString())
//          }
//          head mustBe last
//        case Failure(e) =>
//          e.printStackTrace()
//          fail()
//      }
//    }
//
//    "should ensure that the leading tone always leads to the tonic" in {
//      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
//      val tonic = Random.between(3, maxTonic)
//      // List(G2, C3, G3, F#/Gb3, G3, F#/Gb3, G3, F#/Gb3, A2, F#/Gb2, G2)
//      counterpointRecursiveService.generateCantusFirmus() match {
//        case Success(cantusFirmus) =>
//          cantusFirmus.zipWithIndex.map {
//            case (note, i) =>
//              if (AVAILABLE_CANTUS_FIRMUS_NOTES
//                .filter(note =>
//                  note.filterNot(c => c.isDigit) == AVAILABLE_CANTUS_FIRMUS_NOTES(tonic - 1).filterNot(c => c.isDigit)
//                ).contains(note)) {
//                if (cantusFirmus(i + 1) != AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)) {
//                  println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
//                  println(cantusFirmus.toString())
//                }
//                cantusFirmus(i + 1) mustBe AVAILABLE_CANTUS_FIRMUS_NOTES(AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note) + 1)
//              }
//          }
//        case Failure(e) =>
//          e.printStackTrace()
//          fail()
//      }
//    }
//
//    "should ensure that the range between the lowest note and the highest note is no larger than a tenth" in {
//      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
//      (randomService.between _).expects(8, 17).returning(8)
//      (randomService.between _).expects(3, maxTonic).returning(3)
//      (randomService.nextInt _).expects(8).returning(7)
//      // would be 10 if it allowed more than a 10th
//      (randomService.nextInt _).expects(8).returning(7)
//      (randomService.nextInt _).expects(6).returning(5)
//      (randomService.nextInt _).expects(7).returning(6)
//      (randomService.nextInt _).expects(5).returning(4)
//      (randomService.nextInt _).expects(1).returning(0)
//
//      // List(G2, G3, D4, C4, D4, A3, A2, G2)
//      counterpointRecursiveService.generateCantusFirmus() match {
//        case Success(cantusFirmus) =>
//          val stepwiseValues = cantusFirmus.map(note => AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(note))
//          if (stepwiseValues.max - stepwiseValues.min > 16) {
//            println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
//            println(cantusFirmus.toString())
//          }
//          stepwiseValues.max - stepwiseValues.min <= 16 mustBe true
//        case Failure(e) =>
//          e.printStackTrace()
//          fail()
//      }
//    }

    "should ensure that each leap greater than a 3rd is " +
      "followed by a stepwise motion in the opposite direction" in {
      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
      (randomService.between _).expects(8, 17).returning(9)
      (randomService.between _).expects(3, maxTonic).returning(5)
      (randomService.nextInt _).expects(9).returning(1)
      (randomService.nextInt _).expects(7).returning(0)
      (randomService.nextInt _).expects(6).returning(5)
      // would be 8 if it didn't force stepwise motion in the opposite direction
      (randomService.nextInt _).expects(1).returning(0)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(6).returning(5)
      (randomService.nextInt _).expects(2).returning(1)
      // OLD: List(A2, F#/Gb2, E2, E3, G#/Ab2, A2, E2, B2, A2)
      // NEW: List(A2, F#/Gb2, E2, E3, D3, F#/Gb3, E3, B2, A2)
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
    }

//    "should ensure that there are no more than two leaps in a row" in {
//      val maxTonic = AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7
//      val tonic = 7
//      (randomService.between _).expects(8, 17).returning(12)
//      (randomService.between _).expects(3, maxTonic).returning(tonic)
//      val notesInKey = counterpointRecursiveService.getInMajorKeyCantusFirmusNotes(AVAILABLE_CANTUS_FIRMUS_NOTES(tonic))
//
//      (randomService.nextInt _).expects(10).returning(6).anyNumberOfTimes()
//      (randomService.nextInt _).expects(1).returning(0).anyNumberOfTimes()
//      (randomService.nextInt _).expects(11).returning(3).anyNumberOfTimes()
//      (randomService.nextInt _).expects(2).returning(1).anyNumberOfTimes()
//      (randomService.nextInt _).expects(8).returning(5).anyNumberOfTimes()
//      (randomService.nextInt _).expects(7).returning(4).anyNumberOfTimes()
//      // FAILURE FOUND WITH THIS CANTUS FIRMUS:
//      // List()
//      // List(B2)
//      // List(B2, E3)
//      // List(B2, E3, D#/Eb3)
//      // List(B2, E3, D#/Eb3, B2)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3, F#/Gb3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3, E3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3, E3, C#/Db3)
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3, E3, C#/Db3, B2)
//      //FAILURE FOUND WITH THIS CANTUS FIRMUS:
//      // List(B2, E3, D#/Eb3, B2, C#/Db3, A#/Bb2, B2, E3, D#/Eb3, E3, C#/Db3, B2)
//      counterpointRecursiveService.generateCantusFirmus() match {
//        case Success(cantusFirmus) =>
//          cantusFirmus.zipWithIndex.map {
//            case (note, i) =>
//              if (i > 2) {
//                val prevNote = cantusFirmus(i - 1)
//                val prevPrevNote = cantusFirmus(i - 2)
//                val prevPrevPrevNote = cantusFirmus(i - 3)
//                if (math.abs(notesInKey.indexOf(prevPrevPrevNote) - notesInKey.indexOf(prevPrevNote)) > 1) {
//                  if (math.abs(notesInKey.indexOf(prevPrevNote) - notesInKey.indexOf(prevNote)) > 1) {
//                    if (math.abs(notesInKey.indexOf(prevNote) - notesInKey.indexOf(note)) > 1) {
//                      println("FAILURE FOUND WITH THIS CANTUS FIRMUS:")
//                      println(cantusFirmus.toString())
//                    }
//                    math.abs(notesInKey.indexOf(prevNote) - notesInKey.indexOf(note)) > 1 mustBe false
//                  }
//                }
//              }
//          }
//        case Failure(e) =>
//          e.printStackTrace()
//          fail()
//      }
//    }

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
