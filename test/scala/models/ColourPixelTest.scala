package models

import org.scalatest.FunSuite

class ColourPixelTest extends FunSuite {
  test("equals, ==") {
    val pix = ColourPixel(1, 2, 3)
    assert(ColourPixel(1, 2, 3) == pix)
    assert(ColourPixel(1, 2, 3).equals(pix))
  }
}
