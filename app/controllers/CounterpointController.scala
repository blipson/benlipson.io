package controllers

import domain.CounterpointResponse
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import net.liftweb.util.StringHelpers
import play.api.mvc._
import service.CounterpointService

import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success}

@Singleton
class CounterpointController @Inject()(
                                        cc: ControllerComponents,
                                        counterpointService: CounterpointService
                                      ) extends AbstractController(cc) {
  implicit val formats: DefaultFormats.type = DefaultFormats

  def generateCounterpoint(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    counterpointService.generateCantusFirmus() match {
      case Success(value) => Ok(StringHelpers.snakify(write(CounterpointResponse(value))))
      case Failure(exception) => InternalServerError(exception.toString)
    }
  }
}
