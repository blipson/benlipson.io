package service

import scala.util.{Failure, Success, Try}

class FirstSpeciesService(var counterpointService: CounterpointService) extends Counterpoint {
  def this() = {
    this(new CounterpointService())
  }

  override def generate(cantusFirmus: List[String]): Try[List[String]] = Success(cantusFirmus)

  override def formatOutput(firstSpecies: List[String]): List[String] =
    counterpointService.formatOutput(firstSpecies)

  def formatInput(cantusFirmus: List[String]): List[String] =
    counterpointService.formatInput(cantusFirmus)
}
