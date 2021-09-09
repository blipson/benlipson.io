package service

import service.CounterpointService.{AVAILABLE_CANTUS_FIRMUS_NOTES, OCTAVE, VALID_MAJOR_KEY_INTERVALS}

import javax.inject.{Inject, Singleton}
import scala.util.{Random, Try}

@Singleton
class CounterpointService @Inject()(randomService: RandomService) {
  def getInMajorKeyCantusFirmusNotes(tonic: String): Seq[String] =
    AVAILABLE_CANTUS_FIRMUS_NOTES.filter(note => {
      noteIsInMajorKey(tonic, note, AVAILABLE_CANTUS_FIRMUS_NOTES.indices
        .filter(interval => intervalIsInMajorKey(interval)))
    })

  private def intervalIsInMajorKey(interval: Int) =
    VALID_MAJOR_KEY_INTERVALS.contains(interval % OCTAVE)

  private def noteIsInMajorKey(tonic: String, note: String, majorKeyIntervals: Seq[Int]) =
    majorKeyIntervals
      .map(interval => {
        getNoteForInterval(tonic, interval)
      })
      .contains(note)

  private def getNoteForInterval(tonic: String, interval: Int) =
    AVAILABLE_CANTUS_FIRMUS_NOTES(
      (AVAILABLE_CANTUS_FIRMUS_NOTES.indexOf(tonic) + interval) %
      (OCTAVE * 2)
    )


  def pickPenultimateNote(tonic: String, inMajorKeyCantusFirmusNotes: Seq[String]): String = {
    val penultimateNotePicker = randomService.between(1, 11)
    if (penultimateNotePicker >= 7) {
      inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) - 1)
    } else {
      inMajorKeyCantusFirmusNotes(inMajorKeyCantusFirmusNotes.indexOf(tonic) + 1)
    }
  }

  def generateCantusFirmus(): Try[List[String]] = Try {
    val length = randomService.between(8, 17)
    val tonic = AVAILABLE_CANTUS_FIRMUS_NOTES(randomService.between(3, AVAILABLE_CANTUS_FIRMUS_NOTES.length - 7))
    val inMajorKeyCantusFirmusNotes = getInMajorKeyCantusFirmusNotes(tonic)
    (1 to length).map(i => {
      if (i == length - 1) {
        pickPenultimateNote(tonic, inMajorKeyCantusFirmusNotes)
      } else {
        tonic
      }
    }).toList
  }

}

object CounterpointService {
  val OCTAVE = 12

  val VALID_MAJOR_KEY_INTERVALS = Set(
    0, 2, 4, 5, 7, 9, 11
  )

  val NOTES = List(
    "E",
    "F",
    "F#/Gb",
    "G",
    "G#/Ab",
    "A",
    "A#/Bb",
    "B",
    "C",
    "C#/Db",
    "D",
    "D#/Eb"
  )

  def GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(numOctaves: Int, startOctave: Int): Seq[String] =
    (0 until NOTES.length * numOctaves).map(noteIdx => {
      val stepsAboveC = OCTAVE - NOTES.slice(NOTES.length - NOTES.indexOf("C"), NOTES.length).length
      val currentOctave = startOctave + math.floor((noteIdx + stepsAboveC) / OCTAVE).toInt
      NOTES(noteIdx % NOTES.length).concat(currentOctave.toString)
    }).toList

  val AVAILABLE_CANTUS_FIRMUS_NOTES: Seq[String] = GENERATE_AVAILABLE_CANTUS_FIRMUS_NOTES(2, 2)
}
