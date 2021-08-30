package service
import javax.inject.{Inject, Singleton}

@Singleton
class HammingService {
  def calculateHammingCode(binaryInput: String): String = {
    "hello dependency injection"
  }
}
