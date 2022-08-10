package models

import org.scalatest.FunSuite

class AsciiPixelTest extends FunSuite {
  test("equals, ==") {
    val px = AsciiPixel(';')
    assert(AsciiPixel(';') == px)
    assert(AsciiPixel(';').equals(px))
  }
  test("toString") {
    assert(AsciiPixel('a').toString == "a")
  }
}
