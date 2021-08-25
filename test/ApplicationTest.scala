import controllers.Application
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ApplicationTest extends PlaySpec {
  "Application controller" should {
    "should have the correct context" in {
      val controller = new Application(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index().apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText.replaceAll(" +", "")  mustBe
        """
                          |
                          |<!DOCTYPE html>
                          |
                          |<html lang="en">
                          |    <head>
                          |        <title>BenLipsonIo</title>
                          |        <title>Scala Getting Started on Heroku</title>
                          |    </head>
                          |    <body>
                          |        <!-- import other pages like this -->
                          |        <a href="/"><span class="glyphicon glyphicon-home"></span> Home</a>
                          |
                          |        <!-- import content from main.scala.html like this -->
                          |
                          |  <p>Content.</p>
                          |
                          |  <div class="alert alert-success text-center" role="alert">
                          |    message
                          |  </div>
                          |
                          |
                          |        Hello, world!
                          |    </body>
                          |</html>
                          |
                          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
