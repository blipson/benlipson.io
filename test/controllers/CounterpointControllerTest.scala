package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.CounterpointRecursiveService

import scala.util.{Failure, Success}

class CounterpointControllerTest extends PlaySpec with MockFactory {
  "Counterpoint controller" should {
    "should call the Counterpoint service to generate the cantus firmus correctly" in {
      val counterpointService = mock[CounterpointRecursiveService]
      (counterpointService.generateCantusFirmus _).expects().returning(Success(List("B3", "D#/Eb3", "E3", "C#/Db3", "A#/Bb3", "B3", "C#/Db4", "B3")))
      contentAsString(
        new CounterpointController(Helpers.stubControllerComponents(), counterpointService)
          .generateCounterpoint()
          .apply(FakeRequest())
      ) mustBe "{\"cantus_firmus\":[\"b3\",\"d#/eb3\",\"e3\",\"c#/db3\",\"a#/bb3\",\"b3\",\"c#/db4\",\"b3\"]}"
    }

    "should handle any failure and return a 500" in {
      val counterpointService = mock[CounterpointRecursiveService]
      (counterpointService.generateCantusFirmus _).expects().returning(Failure(new Exception("Test exception.")))
      contentAsString(
        new CounterpointController(Helpers.stubControllerComponents(), counterpointService)
          .generateCounterpoint()
          .apply(FakeRequest())
      ) mustBe "java.lang.Exception: Test exception."
    }
  }
}