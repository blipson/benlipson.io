package controllers

import play.api.mvc._

import javax.inject.{Inject, Singleton}

@Singleton
class BlogController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def blog(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.blog())
  }

  def onTravel(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.onTravel())
  }

  def onTechnology(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.onTechnology())
  }

  def graphicsHistory(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.graphicsHistory())
  }

  def onSports(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.onSports())
  }

  def functionalEnlightenment(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.functionalEnlightenment())
  }

  def onGod(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.onGod())
  }

  def graphicsNotes(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.graphicsNotes())
  }

  def rendererTgaImages(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.rendererTgaImages())
  }

  def rendererDrawingLines(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.rendererDrawingLines())
  }

  def withAndUnion(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.withAndUnion())
  }
}
