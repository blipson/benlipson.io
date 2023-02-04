package service

import scala.util.Try

trait Counterpoint {
  def generate(cantusFirmus: List[String] = List()): Try[List[String]]
  def format(line: List[String]): List[String]
}
