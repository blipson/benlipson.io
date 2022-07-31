package controllers

import play.api.mvc._

import javax.inject.{Inject, Singleton}

@Singleton
class ProjectsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def projects(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.projects())
  }

  def hammingCodes(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.hammingCodes())
  }

  def graphTv(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.graphTv())
  }

  def restaurantPicker(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.restaurantPicker())
  }

  def counterpoint(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.counterpoint())
  }

  def webGL2(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.webGL2())
  }
}
