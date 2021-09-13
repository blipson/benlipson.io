package service

import util.Utils

import scala.annotation.tailrec
import scala.util.{Failure, Try}

class HammingService {
  def calculateHammingCode(binaryInput: String): Try[String] = Try {
    checkForInvalidInput(binaryInput) match {
      case Some(toReturn) => return toReturn
      case None => // do nothing
    }

    def hammingCodeWithPlaceholders: List[String] = insertPlaceholderParityBits(
      binaryInput,
      binaryInput.length + getNumberOfParityBits(binaryInput)
    )

    hammingCodeWithPlaceholders.zipWithIndex.map {
      case (bit, i) =>
        if (bit != "0" && bit != "1") {
          calculateParityBitValue(hammingCodeWithPlaceholders, i + 1)
        } else {
          bit
        }
    }.mkString("")
  }

  @tailrec
  final def getNumberOfParityBits(binaryInput: String, acc: Int = 0): Int =
    if (scala.math.pow(2, acc) >= binaryInput.length + acc + 1) {
      acc
    } else {
      getNumberOfParityBits(binaryInput, acc + 1)
    }

  @tailrec
  final def insertPlaceholderParityBits(binaryInput: String, hammingLength: Int, inputPosition: Int = 0, hammingCode: List[String] = List()): List[String] =
    if (hammingCode.length == hammingLength) {
      hammingCode
    } else if (Utils.isPowerOfTwo(hammingCode.length + 1)) {
      insertPlaceholderParityBits(binaryInput, hammingLength, inputPosition, hammingCode :+ "2")
    } else {
      insertPlaceholderParityBits(binaryInput, hammingLength, inputPosition + 1, hammingCode :+ binaryInput(inputPosition).toString)
    }

  def calculateParityBitValue(hammingCode: List[String], power: Int): Int =
    calculateParityBitValueHelper(power, hammingCode, power - 1)

  private def checkForInvalidInput(binaryInput: String): Option[Failure[Nothing]] = {
    checkForEmptyInput(binaryInput) match {
      case Some(toReturn) => return toReturn
      case None => // do nothing
    }
    checkForTooLongInput(binaryInput) match {
      case Some(toReturn) => return toReturn
      case None => // do nothing
    }
    checkForNonBinaryInput(binaryInput) match {
      case Some(toReturn) => return toReturn
      case None => // do nothing
    }
    None
  }

  private def checkForNonBinaryInput(binaryInput: String): Option[Option[Failure[Nothing]]] =
    "[^01]+".r findFirstIn binaryInput match {
      case Some(_) => Some(Some(Failure(new Exception("Only 1s and 0s allowed."))))
      case None => None
    }

  private def checkForTooLongInput(binaryInput: String): Option[Option[Failure[Nothing]]] =
    if (binaryInput.length > 100) {
      Some(Some(Failure(new Exception("Max length of 100."))))
    } else {
      None
    }

  private def checkForEmptyInput(binaryInput: String): Option[Option[Failure[Nothing]]] =
    if (binaryInput.isEmpty) {
      Some(Some(Failure(new Exception("Binary input is required."))))
    } else {
      None
    }

  @tailrec
  private def calculateParityBitValueHelper(power: Int, hammingCode: List[String], position: Int, skipper: Int = 0, accum: Int = 0): Int =
    if (position >= hammingCode.length) {
      accum
    } else if (skipper % (power * 2) < power) {
      calculateParityBitValueHelper(power, hammingCode, position + 1, skipper + 1, (accum + hammingCode(position).toInt % 2) % 2)
    } else {
      calculateParityBitValueHelper(power, hammingCode, position + 1, skipper + 1, accum)
    }
}
