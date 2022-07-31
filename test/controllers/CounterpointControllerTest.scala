package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.CounterpointService

import scala.util.{Failure, Success}

class CounterpointControllerTest extends PlaySpec with MockFactory {

  "Counterpoint controller" should {
    val counterpointService = mock[CounterpointService]
    val counterpointController = new CounterpointController(Helpers.stubControllerComponents(), counterpointService)
    val cantusFirmus = List("D3", "G3", "F#/Gb3", "B3", "A3", "F#/Gb3", "G3", "F#/Gb3", "E3", "D3")
    val firstSpecies = List("A3", "B3", "C4", "D4", "E4", "D4", "B3", "A3", "C4", "D4")

    "should call the Counterpoint service to generate and format the cantus firmus correctly" in {
      (counterpointService.generateCantusFirmus _).expects().returning(Success(cantusFirmus))
      (counterpointService.formatVoice _).expects(cantusFirmus).returning(List("d/3", "g/3", "f#/3", "b/3", "a/3", "f#/3", "g/3", "f#/3", "e/3", "d/3"))
      contentAsString(
        counterpointController
          .generateCantusFirmus()
          .apply(FakeRequest())
      ) mustBe "{\"cantus_firmus\":[\"d/3\",\"g/3\",\"f#/3\",\"b/3\",\"a/3\",\"f#/3\",\"g/3\",\"f#/3\",\"e/3\",\"d/3\"],\"raw\":[\"d3\",\"g3\",\"f#/gb3\",\"b3\",\"a3\",\"f#/gb3\",\"g3\",\"f#/gb3\",\"e3\",\"d3\"]}"
    }

    "should handle any failure and return a 500 when generating cantus firmus" in {
      (counterpointService.generateCantusFirmus _).expects().returning(Failure(new Exception("Test exception.")))
      contentAsString(
        counterpointController
          .generateCantusFirmus()
          .apply(FakeRequest())
      ) mustBe "java.lang.Exception: Test exception."
    }

    "should call the Counterpoint service to generate and format the first species correctly" in {
      (counterpointService.generateFirstSpecies _).expects(cantusFirmus).returning(Success(firstSpecies))
      (counterpointService.formatVoice _).expects(firstSpecies).returning(List("a/3", "b/3", "c/4", "d/4", "e/4", "d/4", "b/3", "a/3", "c/4", "d/4"))
      contentAsString(
        counterpointController.generateFirstSpecies()
          .apply(FakeRequest().withJsonBody(Json.parse("[\"d/3\",\"g/3\",\"f#/3\",\"b/3\",\"a/3\",\"f#/3\",\"g/3\",\"f#/3\",\"e/3\",\"d/3\"]")))
      ) mustBe "{\"first_species\":[\"a/3\",\"b/3\",\"c/4\",\"d/4\",\"e/4\",\"d/4\",\"b/3\",\"a/3\",\"c/4\",\"d/4\"]}"
    }

    "should handle any failure and return a 500 when generating first species" in {
      (counterpointService.generateFirstSpecies _).expects(cantusFirmus).returning(Failure(new Exception("Test exception.")))
      contentAsString(
        counterpointController
          .generateFirstSpecies()
          .apply(FakeRequest().withJsonBody(Json.parse("[\"d/3\",\"g/3\",\"f#/3\",\"b/3\",\"a/3\",\"f#/3\",\"g/3\",\"f#/3\",\"e/3\",\"d/3\"]")))
      ) mustBe "java.lang.Exception: Test exception."
    }

    "should handle empty cantus firmus for first species" in {
      contentAsString(
        counterpointController
          .generateFirstSpecies()
          .apply(FakeRequest().withJsonBody(Json.parse("")))
      ) mustBe "java.lang.Exception: Test exception."
    }
  }
}