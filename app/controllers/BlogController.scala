package controllers

import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

@Singleton
class BlogController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def blog(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.blog())
  }
}
