import controllers.HammingController
import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.HammingService

class HammingControllerTest extends PlaySpec with MockFactory {
  "Hamming controller" should {
    "should call the Hamming Service to calculate hamming code and then format the response correctly" in {
      val hammingService = mock[HammingService]
      (hammingService.calculateHammingCode _).expects("1101").returning("10101010")
      contentAsString(
        new HammingController(Helpers.stubControllerComponents(), hammingService)
          .calculateHammingCode("1101")
          .apply(FakeRequest())
      ) mustBe "{\"hamming_code\":\"10101010\"}"
    }
  }
}