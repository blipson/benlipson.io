package service

import javax.inject.Singleton
import scala.util.Random

@Singleton
class RandomService {
  def between(minInclusive: Int, maxExclusive: Int): Int = Random.between(minInclusive, maxExclusive)
  def nextDouble(): Double = Random.nextDouble()
  def nextInt(n: Int): Int = Random.nextInt(n)
}
