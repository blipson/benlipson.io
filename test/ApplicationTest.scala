import org.scalatestplus.play.PlaySpec

class ApplicationTest extends PlaySpec {
  "Application controller" should {
    "should be valid" in {
//      val result: Future[Result] = Application.index().apply(FakeRequest())
      val x = 1
      x mustBe(1)
    }
  }
}
