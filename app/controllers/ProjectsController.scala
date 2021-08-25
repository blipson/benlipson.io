package controllers

import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

@Singleton
class ProjectsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def projects(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.projects())
  }
}