package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.CounterpointService

import scala.util.{Failure, Success}

class CounterpointControllerTest extends PlaySpec with MockFactory {
  "Counterpoint controller" should {
    "should call the Counterpoint service to generate and format the cantus firmus correctly" in {
      val counterpointService = mock[CounterpointService]
      val cantusFirmus = List("B3", "D#/Eb3", "E3", "C#/Db3", "A#/Bb3", "B3", "C#/Db4", "B3")
      (counterpointService.generateCantusFirmus _).expects().returning(Success(cantusFirmus))
      (counterpointService.formatCantusFirmus _).expects(cantusFirmus).returning(List("b/3", "d#/3", "e/3", "c#/3", "a#/3", "b/3", "c#/4", "b/3"))
      contentAsString(
        new CounterpointController(Helpers.stubControllerComponents(), counterpointService)
          .generateCantusFirmus()
          .apply(FakeRequest())
      ) mustBe "{\"cantus_firmus\":[\"b/3\",\"d#/3\",\"e/3\",\"c#/3\",\"a#/3\",\"b/3\",\"c#/4\",\"b/3\"]}"
    }

    "should handle any failure and return a 500" in {
      val counterpointService = mock[CounterpointService]
      (counterpointService.generateCantusFirmus _).expects().returning(Failure(new Exception("Test exception.")))
      contentAsString(
        new CounterpointController(Helpers.stubControllerComponents(), counterpointService)
          .generateCantusFirmus()
          .apply(FakeRequest())
      ) mustBe "java.lang.Exception: Test exception."
    }
  }
}