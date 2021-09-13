package util

import org.scalatestplus.play.PlaySpec

class UtilsTest extends PlaySpec {
  "Utils service" should {
    "should determine if a number is a power of 2" in {
      Utils.isPowerOfTwo(0) mustBe true
      Utils.isPowerOfTwo(1) mustBe true
      Utils.isPowerOfTwo(2) mustBe true
      Utils.isPowerOfTwo(3) mustBe false
      Utils.isPowerOfTwo(4) mustBe true
      Utils.isPowerOfTwo(5) mustBe false
      Utils.isPowerOfTwo(6) mustBe false
      Utils.isPowerOfTwo(7) mustBe false
      Utils.isPowerOfTwo(8) mustBe true
      Utils.isPowerOfTwo(9) mustBe false
      Utils.isPowerOfTwo(10) mustBe false
      Utils.isPowerOfTwo(11) mustBe false
      Utils.isPowerOfTwo(12) mustBe false
      Utils.isPowerOfTwo(13) mustBe false
      Utils.isPowerOfTwo(14) mustBe false
      Utils.isPowerOfTwo(15) mustBe false
      Utils.isPowerOfTwo(16) mustBe true
      Utils.isPowerOfTwo(239) mustBe false
      Utils.isPowerOfTwo(256) mustBe true
      Utils.isPowerOfTwo(500) mustBe false
      Utils.isPowerOfTwo(512) mustBe true
      Utils.isPowerOfTwo(1000) mustBe false
      Utils.isPowerOfTwo(1024) mustBe true
    }
  }
}
