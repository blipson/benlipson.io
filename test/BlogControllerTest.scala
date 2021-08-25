import controllers.BlogController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class BlogControllerTest extends PlaySpec {
  "Blog controller" should {
    "should display the blog page" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.blog().apply(FakeRequest())
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
        |  <body>
        |        <h1>Blog</h1>
        |        <ul>
        |            <li>
        |                <a href="/blog/renderer/drawinglines">Building a 3D Renderer From Scratch, Part 2: Drawing Lines</a>
        |                <br><i>
        |                07:45 PM - 24 February, 2020
        |            </i>
        |            </li>
        |            <li>
        |                <a href="/blog/renderer/tgaimages">Building a 3D Renderer From Scratch, Part 1: TGA Images</a>
        |                <br><i>
        |                04:30 PM - 19 February, 2020
        |            </i>
        |            </li>
        |            <li>
        |                <a href="/blog/graphicsnotes">Graphics Programming Notes</a>
        |                <br><i>
        |                04:30 PM - 06 November, 2019
        |            </i>
        |                <ul>
        |                    <li>Every few months I spend about a week diving into graphics programming stuff for fun. The problem: each time I've forgotten so much about it that I have to relearn a lot of what I previously already looked at. While each subsequent time is getting faster and faster, it's still rather annoying. So I've compiled this list of things that I seem to have to remind myself of every time in the hopes that it'll go faster in the future.</li>
        |                </ul>
        |            </li>
        |            <li>
        |                <a href="/blog/ongod">On God</a>
        |                <br><i>
        |                02:30 AM - 30 July, 2019
        |            </i>
        |                <ul>
        |                    <li>I've spent a lot of time on this one.</li>
        |                </ul>
        |            </li>
        |            <li>
        |                <a href="/blog/functionalenlightenment">I Pushed My Feeble Imperative Consciousness to a New Plane of Existence and So Can You</a>
        |                <br><i>
        |                    12:30 AM - 15 December, 2018
        |                </i>
        |                <ul>
        |                    <li>The internet is just tubes, our brains are disjoint, consciousness and substance are derived from monadic structures, and infinite break points.</li>
        |                </ul>
        |            </li>
        |            <li>
        |                <a href="/blog/onsports">On Sports</a>
        |                <br><i>
        |                    11:30 PM - 15 April, 2018
        |                </i>
        |                <ul>
        |                    <li>I love sports. But I have a gut feeling that maybe I shouldn't. So I tasked myself with investigating why I feel this way and what I should do about it. Then I wrote it down for you to read! I also dunk on utilitarianism, so get ready for some fun ranting.</li>
        |                </ul>
        |            </li>
        |            <li>
        |                <a href="/blog/graphicshistory">A Brief History and Summary of Graphics Programming</a>
        |                <br><i>
        |                    12:30 AM - 09 February, 2018
        |                </i>
        |                <ul>
        |                    <li>In college, nearly every project I did for class had some aspect of 3D graphics. But I always only learned what I needed. This post satisfies my curiosity to learn how it all works without any tunnel vision.</li>
        |                </ul>
        |            </li>
        |            <li>
        |                <a href="/blog/ontechnology">On Technology</a>
        |                <br><i>
        |                    8:30 PM - 08 November, 2017
        |                </i>
        |                <ul>
        |                    <li>Technological progress is simultaneously the driving force behind modern capitalism, and our only potential salvation from it. So how can we use it correctly? What do we need to change before we can begin work in earnest?</li>
        |                </ul>
        |            </li>
        |            <li>
        |                <a href="/blog/ontravel">On Travel</a>
        |                <br><i>
        |                    10:30 PM - 25 October, 2017
        |                </i>
        |                <ul>
        |                    <li>The pull towards adventure, excitement, and leisure. Is it worth it? How can I do it in a healthy and ethical way? Are good ethics even possible to achieve? Thoughts on these questions and more!</li>
        |                </ul>
        |            </li>
        |        </ul>
        |    </body>
        |</html>
        |""".stripMargin.replaceAll(" +", "")
    }
  }
}
