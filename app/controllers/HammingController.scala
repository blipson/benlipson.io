package controllers

import domain.HammingCodeResponse
import net.liftweb.json.DefaultFormats
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import service.HammingService
import net.liftweb.json.Serialization.write
import net.liftweb.util.StringHelpers

import javax.inject.{Inject, Singleton}

@Singleton
class HammingController @Inject()(cc: ControllerComponents, hammingService: HammingService) extends AbstractController(cc) {
  implicit val formats: DefaultFormats.type = DefaultFormats
  def calculateHammingCode(input: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    def hammingCode: String = hammingService.calculateHammingCode(input)
    Ok(StringHelpers.snakify(write(HammingCodeResponse(hammingCode))))
  }
}
