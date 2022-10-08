package controllers

import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class HomeControllerTest extends PlaySpec {
  "Home controller" should {
    "should display the home page" in {
      val controller = new HomeController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.home().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="en">
          |  <head>
          |    <meta charset="utf-8">
          |    <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |    <meta name="viewport" content="width=device-width, initial-scale=1">
          |    <link rel="shortcut icon" href="#" />
          |    <title>Lipson</title>
          |  </head>
          |
          |  <body>
          |    <h1>Ben Lipson</h1>
          |    <p>Software Engineer, musician, aspiring post-contemporary purely functional automaton.</p>
          |    <p>Hobbies: Dancing, cooking, writing, thinking too hard.</p>
          |    <a href="/blog">Blog</a>
          |    <a href="/projects">Projects</a>
          |    <a href="https://github.com/blipson" target="_blank">Code</a>
          |    <a href="https://github.com/blipson/resume" target="_blank">Resume</a>
          |  </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
