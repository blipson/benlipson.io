package service

import javax.inject.Singleton
import scala.util.Random

@Singleton
class RandomService {
  def between(minInclusive: Int, maxExclusive: Int): Int = Random.between(minInclusive, maxExclusive)
}
