package generators

import org.scalatest.FunSuite

class ColourPixelImageGeneratorTest extends FunSuite {
  test("Only generate") {
    for (i <- 0 until 100) {
      try {
        val gen = new ColourPixelImageGenerator
        val img = gen.generate
      } catch {
        case e: Exception => assert(false)
      }
      assert(true)
    }

  }


}
