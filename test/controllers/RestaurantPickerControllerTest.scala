package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}
import service.RestaurantPickerService

class RestaurantPickerControllerTest extends PlaySpec with MockFactory {
  "Restaurant Picker controller" should {
    "should call the Restaurant Picker service to generate a random restaurant" in {
      val restaurantPickerService = mock[RestaurantPickerService]
      (restaurantPickerService.pickRestaurant _).expects().returning(Some("Test Restaurant"))
      contentAsString(
        new RestaurantPickerController(Helpers.stubControllerComponents(), restaurantPickerService)
          .pickRestaurant()
          .apply(FakeRequest())
      ) mustBe "{\"restaurant\":\"Test Restaurant\"}"
    }

    "should handle None response by defaulting to Tea House" in {
      val restaurantPickerService = mock[RestaurantPickerService]
      (restaurantPickerService.pickRestaurant _).expects().returning(None)
      contentAsString(
        new RestaurantPickerController(Helpers.stubControllerComponents(), restaurantPickerService)
          .pickRestaurant()
          .apply(FakeRequest())
      ) mustBe "{\"restaurant\":\"Tea House\"}"
    }
  }
}
