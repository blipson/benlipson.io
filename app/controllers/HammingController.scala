package controllers

import domain.HammingCodeResponse
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import net.liftweb.util.StringHelpers
import play.api.mvc._
import service.HammingService

import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success}

@Singleton
class HammingController @Inject()(cc: ControllerComponents, hammingService: HammingService) extends AbstractController(cc) {
  implicit val formats: DefaultFormats.type = DefaultFormats

  def calculateHammingCode(input: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    hammingService.calculateHammingCode(input) match {
      case Success(value) => Ok(StringHelpers.snakify(write(HammingCodeResponse(value))))
      case Failure(exception) => InternalServerError(exception.toString)
    }
  }
}
