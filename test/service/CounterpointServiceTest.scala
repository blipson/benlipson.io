package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import service.CounterpointServiceConstants.GET_ALL_NOTES_BETWEEN_TWO_NOTES

class CounterpointServiceTest extends PlaySpec with MockFactory {
  val randomService: RandomService = mock[RandomService]
  val counterpointService = new CounterpointService(randomService)

  "Counterpoint service" should {
    "should convert a note to upper case" in {
      counterpointService.convertNoteToUpperCase("a4") mustBe "A4"
      counterpointService.convertNoteToUpperCase("d#/eb2") mustBe "D#/Eb2"
    }

    "should format a cantus firmus in a sharp key" in {
      val cantusFirmus = List("B3", "D#/Eb3", "E3", "C#/Db3", "A#/Bb3", "B3", "C#/Db4", "B3")
      counterpointService.formatVoice(cantusFirmus) mustBe List("b/3", "d#/3", "e/3", "c#/3", "a#/3", "b/3", "c#/4", "b/3")
    }

    "should format a cantus firmus in a flat key that starts with a secondary note" in {
      val cantusFirmus = List("D#/Eb3", "C3", "A#/Bb2", "G#/Ab2", "F2", "G2", "G3", "F3", "D3", "D#/Eb3")
      counterpointService.formatVoice(cantusFirmus) mustBe List("eb/3", "c/3", "bb/2", "ab/2", "f/2", "g/2", "g/3", "f/3", "d/3", "eb/3")
    }

    "should format a cantus firmus in a flat key that starts with a primary note" in {
      val cantusFirmus = List("F3", "A3", "A#/Bb3", "G3", "A3", "G3", "E3", "F3", "D3", "D4", "C4", "E3", "F3")
      counterpointService.formatVoice(cantusFirmus) mustBe List("f/3", "a/3", "bb/3", "g/3", "a/3", "g/3", "e/3", "f/3", "d/3", "d/4", "c/4", "e/3", "f/3")
    }

    "should format a cantus firmus in C" in {
      val cantusFirmus = List("C3", "D3", "F3", "D3", "E3", "C3", "A3", "G3", "B2", "C3")
      counterpointService.formatVoice(cantusFirmus) mustBe List("c/3", "d/3", "f/3", "d/3", "e/3", "c/3", "a/3", "g/3", "b/2", "c/3")
    }

    "should construct a new service" in {
      new CounterpointService()
    }

    "should get all notes between two notes when it has to add an octave" in {
      GET_ALL_NOTES_BETWEEN_TWO_NOTES("D3", "F#/Gb5") mustBe
        List(
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
          "D#/Eb5",
          "E5",
          "F5",
          "F#/Gb5"
        )
    }

    "should get all notes between two notes when it does not have to add an octave" in {
      GET_ALL_NOTES_BETWEEN_TWO_NOTES("A#/Bb2", "G5") mustBe
        List(
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
          "D#/Eb5",
          "E5",
          "F5",
          "F#/Gb5",
          "G5"
        )
    }
  }
}
