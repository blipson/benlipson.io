package controllers

import domain.CounterpointResponse
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import net.liftweb.util.StringHelpers
import play.api.libs.json.Json
import play.api.mvc._
import service.{CantusFirmusService, FirstSpeciesService}

import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success}

@Singleton
class CounterpointController @Inject()(
                                        cc: ControllerComponents,
                                        cantusFirmusService: CantusFirmusService,
                                        firstSpeciesService: FirstSpeciesService
                                      ) extends AbstractController(cc) {
  implicit val formats: DefaultFormats.type = DefaultFormats

  def generateCantusFirmus(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    cantusFirmusService.generate() match {
      case Success(value) =>
        //        println(value)
        //        println(cantusFirmusService.format(value))
        Ok(StringHelpers.snakify(write(CounterpointResponse(cantusFirmusService.formatOutput(value)))))
      case Failure(exception) => InternalServerError(exception.toString)
    }
  }

  def generateFirstSpecies(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val cantusFirmus = request.body.asJson.get.toString()
    firstSpeciesService.generate(firstSpeciesService.formatInput(Json.parse(cantusFirmus).as[List[String]])) match {
      case Success(value) => Ok(StringHelpers.snakify(write(CounterpointResponse(firstSpeciesService.formatOutput(value)))))
      case Failure(exception) => InternalServerError(exception.toString)
    }
  }
}
