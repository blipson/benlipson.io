package service

import org.scalatestplus.play.PlaySpec

import scala.util.{Failure, Success}

class HammingServiceTest extends PlaySpec {
  def hammingService = new HammingService()
  "Hamming service" should {
    "should calculate the hamming code for a binary input" in {
      Map(
        ("1", "111"),
        ("11", "01111"),
        ("101", "101101"),
        ("1101", "1010101"),
        ("11010", "101010100"),
        ("110100", "1010101000"),
        ("1101001", "01101011001"),
        ("10011010", "011100101010"),
        ("110110111", "0110101010111"),
        ("1101101111", "00111011101111"),
        ("11010011011", "111110100011011"),
        ("110100110111", "01111010001101111")
      ).map(inAndOut => {
        hammingService.calculateHammingCode(inAndOut._1) mustBe Success(inAndOut._2)
      })
    }

    "should calculate the correct number of parity bits" in {
      Map(
        ("1", 2),
        ("11", 3),
        ("101", 3),
        ("1101", 3),
        ("11010", 4),
        ("110100", 4),
        ("1101001", 4),
        ("10011010", 4),
        ("110110111", 4),
        ("1101101111", 4),
        ("11010011011", 4),
        ("110100110111", 5)).map(inAndOut => {
        hammingService.getNumberOfParityBits(inAndOut._1) mustBe inAndOut._2
      })
    }

    "should insert the parity bits in the correct positions" in {
      Map(
        ("1", "221"),
        ("11", "22121"),
        ("101", "221201"),
        ("1101", "2212101"),
        ("11010", "221210120"),
        ("110100", "2212101200"),
        ("1101001", "22121012001"),
        ("10011010", "221200121010"),
        ("110110111", "2212101210111"),
        ("1101101111", "22121012101111"),
        ("11010011011", "221210120011011"),
        ("110100110111", "22121012001101121")
      ).map(inAndOut => {
        hammingService.insertPlaceholderParityBits(inAndOut._1, inAndOut._2.length).mkString("") mustBe inAndOut._2
      })
    }

    "should calculate parity bit values correctly" in {
      def input = List(
        "2", "2", "1", "2", "1", "0", "1", "2",
        "0", "0", "1", "1", "0", "1", "1", "2", "1"
      )

      hammingService.calculateParityBitValue(input, 1) mustBe 0
      hammingService.calculateParityBitValue(input, 2) mustBe 1
      hammingService.calculateParityBitValue(input, 4) mustBe 1
      hammingService.calculateParityBitValue(input, 8) mustBe 0
      hammingService.calculateParityBitValue(input, 16) mustBe 1
    }

    "should error out when anything other than a 1 or a 0 is entered" in {
      Set("1210101", "3", "5019", "sda", "-+{=", "\\\\_|", "219fsa-+{_\\*_=[[").map(input => {
        hammingService.calculateHammingCode(input) match {
          case Success(_) => fail()
          case Failure(e) => e.getMessage mustBe "Only 1s and 0s allowed."
        }
      })
    }

    "should error out when nothing is entered" in {
      hammingService.calculateHammingCode("") match {
        case Success(_) => fail()
        case Failure(e) => e.getMessage mustBe "Binary input is required."
      }
    }

    "should have a max length of 100" in {
      hammingService.calculateHammingCode(
        "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
      ) match {
        case Success(_) => fail()
        case Failure(e) => e.getMessage mustBe("Max length of 100.")
      }
    }
  }
}
