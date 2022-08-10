package loaders.random

import models.ColourPixelImage
import org.scalatest.FunSuite

class RandomImageLoaderTest extends FunSuite {
  test("Load random image") {
    val loader = new RandomImageLoader
    val img = loader.load
    assert(img != null)
    assert(img.isInstanceOf[ColourPixelImage])
  }
}
