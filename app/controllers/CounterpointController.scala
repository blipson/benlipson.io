package controllers

import domain.{CounterpointResponse, FirstSpeciesResponse}
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import net.liftweb.util.StringHelpers
import play.api.libs.json.Json
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

  def generateCantusFirmus(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    counterpointService.generateCantusFirmus() match {
      case Success(value) => Ok(StringHelpers.snakify(write(CounterpointResponse(counterpointService.formatVoice(value), value))))
      case Failure(exception) => InternalServerError(exception.toString)
    }
  }

  def generateFirstSpecies(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val cantusFirmus = request.body.asJson.get.toString()
    println(Json.parse(cantusFirmus).as[List[String]])
    counterpointService.generateFirstSpecies(Json.parse(cantusFirmus).as[List[String]].map(note => counterpointService.convertNoteToUpperCase(note))) match {
      case Success(value) => Ok(StringHelpers.snakify(write(FirstSpeciesResponse(counterpointService.formatVoice(value)))))
      case Failure(exception) => InternalServerError(exception.toString)
    }
  }
}
