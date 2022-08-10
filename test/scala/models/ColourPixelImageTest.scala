package models

import org.scalatest.FunSuite

class ColourPixelImageTest extends FunSuite {
  test("equals, ==") {
    val img = ColourPixelImage(Array(Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3))))
    assert(ColourPixelImage(Array(Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3)))) == img)
    assert(ColourPixelImage(Array(Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3)))).equals(img))
  }
  test("not equals, different size") {
    val img = ColourPixelImage(Array(Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3))))
    assert(!ColourPixelImage(Array(Array(ColourPixel(0, 0, 0)))).equals(img))
  }
  test("not equals, same size") {
    val img = ColourPixelImage(Array(Array(ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 3))))
    assert(!ColourPixelImage(Array(Array(ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 4)))).equals(img))
  }
  test("Picture with different length of rows") {
    try {
      val img = new ColourPixelImage(Array(Array(ColourPixel(1, 2, 3)), Array(ColourPixel(1, 2, 3), ColourPixel(1, 2, 3))))
      assert(false)
    } catch {
      case _: Throwable => assert(true)
    }
  }
  test("Invalid image of size nx0") {
    try {
      val img = new ColourPixelImage(Array(Array(), Array()))
      assert(false)
    } catch {
      case _: Throwable => assert(true)
    }
  }
  test("Invalid image of size 0x0") {
    try {
      val img = new ColourPixelImage(Array())
      assert(false)
    } catch {
      case _: Throwable => assert(true)
    }
  }
}
