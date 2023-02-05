package service

import org.scalatestplus.play.PlaySpec

class FirstSpeciesServiceTest extends PlaySpec {
  val firstSpeciesService = new FirstSpeciesService(new CounterpointService())

  "First species service" should {
    "should format the input correctly for a sharp key" in {
      firstSpeciesService
        .formatInput(
          List("d/3", "g/3", "f#/3", "b/3", "a/3", "f#/3", "g/3", "f#/3", "e/3", "d/3")
        ) mustBe List("D3", "G3", "F#/Gb3", "B3", "A3", "F#/Gb3", "G3", "F#/Gb3", "E3", "D3")
    }

    "should format the input correctly for a flat key" in {
      firstSpeciesService
        .formatInput(
          List("ab/3", "f/3", "eb/3", "f/3", "c/4", "bb/3", "g/3", "ab/3")
        ) mustBe List("G#/Ab3", "F3", "D#/Eb3", "F3", "C4", "A#/Bb3", "G3", "G#/Ab3")
    }
  }
}
