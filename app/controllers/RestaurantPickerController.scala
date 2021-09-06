package controllers

import domain.RestaurantResponse
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import service.RestaurantPickerService

import javax.inject.{Inject, Singleton}

@Singleton
class RestaurantPickerController @Inject()(cc: ControllerComponents, restaurantPickerService: RestaurantPickerService) extends AbstractController(cc) {
  implicit val formats: DefaultFormats.type = DefaultFormats
  def pickRestaurant(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    restaurantPickerService.pickRestaurant() match {
      case Some(restaurant) => Ok(write(RestaurantResponse(restaurant)))
      case None => Ok(write(RestaurantResponse("Tea House")))
    }
  }
}
