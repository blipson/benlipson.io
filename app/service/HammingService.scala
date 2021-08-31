package service

import javax.inject.Singleton
import scala.annotation.tailrec
import scala.util.{Failure, Try}

@Singleton
class HammingService {
  def calculateHammingCode(binaryInput: String): Try[String] = Try {
    if (binaryInput.isEmpty) {
      return Failure(new Exception("Binary input is required."))
    }
    if (binaryInput.length > 100) {
      return Failure(new Exception("Max length of 100."))
    }
    "[^01]+".r findFirstIn binaryInput match {
      case Some(_) => return Failure(new Exception("Only 1s and 0s allowed."))
      case None => // do nothing
    }
    def hammingCodeWithPlaceholders: List[String] = insertParityBits(
      binaryInput,
      binaryInput.length + getNumberOfParityBits(binaryInput)
    )
    hammingCodeWithPlaceholders.zipWithIndex.map {
      case (bit, i) =>
        if (bit == "2") {
          calculateParityBitValue(hammingCodeWithPlaceholders, i + 1)
        } else {
          bit
        }
    }.mkString("")
  }

  @tailrec
  final def getNumberOfParityBits(binaryInput: String, acc: Int = 0): Int = {
    if (scala.math.pow(2, acc) >= binaryInput.length + acc + 1) {
      acc
    } else {
      getNumberOfParityBits(binaryInput, acc + 1)
    }
  }

  def isPowerOfTwo(x: Int): Boolean = (x & (x - 1)) == 0

  @tailrec
  final def insertParityBits(binaryInput: String, hammingLength: Int, inputPosition: Int = 0, hammingCode: List[String] = List()): List[String] = {
    if (hammingCode.length == hammingLength) {
      hammingCode
    } else if (isPowerOfTwo(hammingCode.length + 1)) {
      insertParityBits(binaryInput, hammingLength, inputPosition, hammingCode :+ "2")
    } else {
      insertParityBits(binaryInput, hammingLength, inputPosition + 1, hammingCode :+ binaryInput(inputPosition).toString)
    }
  }

  @tailrec
  private def calculateParityBitValueHelper(power: Int, hammingCode: List[String], position: Int, skipper: Int = 0, accum: Int = 0): Int = {
    if (position >= hammingCode.length) {
      accum
    } else if (skipper % (power * 2)  < power) {
      calculateParityBitValueHelper(power, hammingCode, position + 1, skipper + 1, (accum + hammingCode(position).toInt % 2) % 2)
    } else {
      calculateParityBitValueHelper(power, hammingCode, position + 1, skipper + 1, accum)
    }
  }

  def calculateParityBitValue(hammingCode: List[String], power: Int): Int = {
    calculateParityBitValueHelper(power, hammingCode, power - 1)
  }
}
