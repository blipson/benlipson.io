package service

import org.scalatestplus.play.PlaySpec
import service.CounterpointService.GET_ALL_NOTES_BETWEEN_TWO_NOTES

class CounterpointServiceTest extends PlaySpec {
  // todo should be able to get the correct in major key notes when a tonic is selected below the range of available notes

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
