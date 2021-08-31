package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.HammingService

import scala.util.{Failure, Success}

class HammingControllerTest extends PlaySpec with MockFactory {
  "Hamming controller" should {
    "should call the Hamming Service to calculate hamming code and then format the response correctly" in {
      val hammingService = mock[HammingService]
      (hammingService.calculateHammingCode _).expects("1101").returning(Success("10101010"))
      contentAsString(
        new HammingController(Helpers.stubControllerComponents(), hammingService)
          .calculateHammingCode("1101")
          .apply(FakeRequest())
      ) mustBe "{\"hamming_code\":\"10101010\"}"
    }

    "should handle any failure and return a 500" in {
      val hammingService = mock[HammingService]
      (hammingService.calculateHammingCode _).expects("1101").returning(Failure(new Exception("Test exception.")))
      contentAsString(
        new HammingController(Helpers.stubControllerComponents(), hammingService)
          .calculateHammingCode("1101")
          .apply(FakeRequest())
      ) mustBe "java.lang.Exception: Test exception."
    }
  }
}