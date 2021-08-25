import controllers.ProjectsController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ProjectsControllerTest extends PlaySpec {
  "Projects controller" should {
    "should display the projects page" in {
      val controller = new ProjectsController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.projects().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<html lang="en">
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=1">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>Projects</h1>
          |        <ul>
          |            <li>
          |                <a href="/projects/counterpoint">Counterpoint Generator</a>
          |                <br><i>
          |                6:00 AM - 05 Nov 2019
          |            </i>
          |                <ul>
          |                    <li>A rudimentary cantus firmus generator. It adheres to the following rules:
          |                    <ol>
          |                        <li>Length must be 8-16 notes</li>
          |                        <li>First and last note must be tonic</li>
          |                        <li>Final note must typically be approached by a step above, occasionally by a half step below</li>
          |                        <li>The leading tone always progresses to the tonic</li>
          |                        <li>All notes must be in the correct key</li>
          |                        <li>All note-to-note progressions are melodic consonances (almost, except 3rd to last -> 2nd to last)</li>
          |                        <li>No note may be followed by the same note</li>
          |                        <li>Range (interval between lowest and highest notes) of no more than a tenth</li>
          |                        <li>No more than 2 melodic motions larger a 4th or larger (leaps)</li>
          |                        <li>Any large leaps (fourth or larger) are followed by step in opposite direction</li>
          |                        <li>A single climax (high point) that appears only once in the melody</li>
          |                    </ol>
          |                </ul>
          |            </li>
          |        </ul>
          |        <ul>
          |            <li>
          |                <a href="/projects/restaurantpicker">Restaurant Picker</a>
          |                <br><i>
          |                9:00 PM - 09 Jan 2018
          |            </i>
          |                <ul>
          |                    <li>When I'm going out to eat I can never decide what restaurant to go to. I have the same problem whether I'm alone or with
          |                        friends/on a date. So I built a tool for myself that'll make the decision for me. Doesn't account for dietary restrictions.</li>
          |                </ul>
          |            </li>
          |        </ul>
          |        <ul>
          |            <li>
          |                <a href="/projects/graphtv">Graph TV</a>
          |                <br><i>
          |                9:00 PM - 09 Jan 2018
          |            </i>
          |                <ul>
          |                    <li>One of my favorite websites (KevinInformatics GraphTV) recently went down. It seems that the API key the website uses is
          |                        no longer valid. I decided to get my own API key and replace it. I find myself wanting these metrics
          |                        more often than you might think.</li>
          |                </ul>
          |            </li>
          |        </ul>
          |        <ul>
          |            <li>
          |                <a href="/projects/hamming">Hamm1ng C0des</a>
          |                <br><i>
          |                9:00 PM - 26 Oct 2017
          |            </i>
          |                <ul>
          |                    <li>Have y0u ever w0ndered h0w 0perat1ng systems pr0tect aga1nst data c0rrupt1on when transferr1ng f1les t0 0r fr0m an0ther st0rage
          |                        Dev1ce, 0r when mem0ry gets cached in ram? Hamm1ng c0des ensure err0r detect10n thr0ugh the use 0f
          |                        Par1ty b1ts enc0ded w1th1n the data.</li>
          |                </ul>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}