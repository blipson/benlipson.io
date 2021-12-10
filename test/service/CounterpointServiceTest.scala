package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES}

import scala.util.{Failure, Success}

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  val counterpointService = new CounterpointRecursiveService(randomService)

  "Counterpoint service" should {
    "should format a cantus firmus in a sharp key" in {
      val cantusFirmus = List("B3", "D#/Eb3", "E3", "C#/Db3", "A#/Bb3", "B3", "C#/Db4", "B3")
      counterpointService.formatCantusFirmus(cantusFirmus) mustBe List("b/3", "d#/3", "e/3", "c#/3", "a#/3", "b/3", "c#/4", "b/3")
    }

    "should format a cantus firmus in a flat key that starts with a secondary note" in {
      val cantusFirmus = List("D#/Eb3", "C3", "A#/Bb2", "G#/Ab2", "F2", "G2", "G3", "F3", "D3", "D#/Eb3")
      counterpointService.formatCantusFirmus(cantusFirmus) mustBe List("eb/3", "c/3", "bb/2", "ab/2", "f/2", "g/2", "g/3", "f/3", "d/3", "eb/3")
    }

    "should format a cantus firmus in a flat key that starts with a primary note" in {
      val cantusFirmus = List("F3", "A3", "A#/Bb3", "G3", "A3", "G3", "E3", "F3", "D3", "D4", "C4", "E3", "F3")
      counterpointService.formatCantusFirmus(cantusFirmus) mustBe List("f/3", "a/3", "bb/3", "g/3", "a/3", "g/3", "e/3", "f/3", "d/3", "d/4", "c/4", "e/3", "f/3")
    }

    "should format a cantus firmus in C" in {
      val cantusFirmus = List("C3", "D3", "F3", "D3", "E3", "C3", "A3", "G3", "B2", "C3")
      counterpointService.formatCantusFirmus(cantusFirmus) mustBe List("c/3", "d/3", "f/3", "d/3", "e/3", "c/3", "a/3", "g/3", "b/2", "c/3")
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
