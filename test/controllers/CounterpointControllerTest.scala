package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.{CantusFirmusService, FirstSpeciesService}

import scala.util.{Failure, Success}

class CounterpointControllerTest extends PlaySpec with MockFactory {
  val cantusFirmusService: CantusFirmusService = mock[CantusFirmusService]
  val firstSpeciesService: FirstSpeciesService = mock[FirstSpeciesService]
  val counterpointController = new CounterpointController(Helpers.stubControllerComponents(), cantusFirmusService, firstSpeciesService)
  val cantusFirmus: List[String] = List("D3", "G3", "F#/Gb3", "B3", "A3", "F#/Gb3", "G3", "F#/Gb3", "E3", "D3")
  val firstSpecies: List[String] = List("A3", "B3", "C4", "D4", "E4", "D4", "B3", "A3", "C4", "D4")

  "Counterpoint controller" should {
    "should call the cantus firmus service to generate and format the cantus firmus correctly" in {
      (cantusFirmusService.generate _).expects(List()).returning(Success(cantusFirmus))
      (cantusFirmusService.formatOutput _).expects(cantusFirmus).returning(List("b/3", "d#/3", "e/3", "c#/3", "a#/3", "b/3", "c#/4", "b/3"))
      contentAsString(
        counterpointController
          .generateCantusFirmus()
          .apply(FakeRequest())
      ) mustBe "{\"line\":[\"b/3\",\"d#/3\",\"e/3\",\"c#/3\",\"a#/3\",\"b/3\",\"c#/4\",\"b/3\"]}"
    }

    "should handle any failure and return a 500" in {
      (cantusFirmusService.generate _).expects(List()).returning(Failure(new Exception("Test exception.")))
      contentAsString(
        counterpointController
          .generateCantusFirmus()
          .apply(FakeRequest())
      ) mustBe "java.lang.Exception: Test exception."
    }

    "should call the first species service to generate and format the first species correctly" in {
      (firstSpeciesService.generate _).expects(cantusFirmus).returning(Success(firstSpecies))
      (firstSpeciesService.formatOutput _).expects(firstSpecies).returning(List("a/3", "b/3", "c/4", "d/4", "e/4", "d/4", "b/3", "a/3", "c/4", "d/4"))
      setUpFirstSpeciesGeneration()

      contentAsString(
        counterpointController
          .generateFirstSpecies()
          .apply(
            FakeRequest()
              .withJsonBody(
                Json.parse("[\"d/3\",\"g/3\",\"f#/3\",\"b/3\",\"a/3\",\"f#/3\",\"g/3\",\"f#/3\",\"e/3\",\"d/3\"]")
              )
          )
      ) mustBe "{\"line\":[\"a/3\",\"b/3\",\"c/4\",\"d/4\",\"e/4\",\"d/4\",\"b/3\",\"a/3\",\"c/4\",\"d/4\"]}"
    }

    "should handle any failure and return a 500 when generating first species" in {
      (firstSpeciesService.generate _).expects(cantusFirmus).returning(Failure(new Exception("Test exception.")))
      setUpFirstSpeciesGeneration()

      contentAsString(
        counterpointController
          .generateFirstSpecies()
          .apply(
            FakeRequest()
              .withJsonBody(
                Json.parse("[\"d/3\",\"g/3\",\"f#/3\",\"b/3\",\"a/3\",\"f#/3\",\"g/3\",\"f#/3\",\"e/3\",\"d/3\"]")
              )
          )
      ) mustBe "java.lang.Exception: Test exception."
    }
    // TODO
    //    "should handle empty cantus firmus for first species" in {
    //      contentAsString(
    //        counterpointController
    //          .generateFirstSpecies()
    //          .apply(FakeRequest().withJsonBody(Json.parse("")))
    //      ) mustBe "java.lang.Exception: Test exception."
    //    }
  }

  private def setUpFirstSpeciesGeneration() = {
    (firstSpeciesService.formatInput _)
      .expects(List("d/3", "g/3", "f#/3", "b/3", "a/3", "f#/3", "g/3", "f#/3", "e/3", "d/3"))
      .returning(List("D3", "G3", "F#/Gb3", "B3", "A3", "F#/Gb3", "G3", "F#/Gb3", "E3", "D3"))
  }
}