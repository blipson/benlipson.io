package service

import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.PlaySpec

class RestaurantPickerServiceTest extends PlaySpec with MockFactory {
  def restaurantPickerService = new RestaurantPickerService()
  "Restaurant Picker service" should {
    "should pick a random restaurant" in {
      restaurantPickerService.pickRestaurant() match {
        case Some(restaurant) => RestaurantPickerService.RESTAURANT_LIST.contains(restaurant) mustBe true
        case None => // do nothing
      }
    }
  }
}
