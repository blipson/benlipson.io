package service

import scala.util.Random

class RandomService {
  def between(minInclusive: Int, maxExclusive: Int): Int = Random.between(minInclusive, maxExclusive)

  def nextDouble(): Double = Random.nextDouble()

  def nextInt(n: Int): Int = Random.nextInt(n)
}
