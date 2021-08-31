package util

object Utils {
  def isPowerOfTwo(x: Int): Boolean = (x & (x - 1)) == 0
}
