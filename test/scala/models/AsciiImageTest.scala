package models

import org.scalatest.FunSuite

class AsciiImageTest extends FunSuite {
  test("equals, ==") {
    val img = new AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b'))))
    assert(AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')))) == img)
    assert(AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')))).equals(img))
  }
  test("not equals, different size") {
    val img = new AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b'))))
    assert(!AsciiImage(Array(Array(AsciiPixel('a')))).equals(img))
  }
  test("not equals, same size") {
    val img = new AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b'))))
    assert(!AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('a')))).equals(img))
  }
  test("toString one row") {
    val img = new AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b'))))
    assert(img.toString == "ab\n")
  }
  test("toString") {
    val img = new AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('a'), AsciiPixel('b'))))
    assert(img.toString == "ab\nab\n")
  }

  test("toString one pix") {
    val img = new AsciiImage(Array(Array(AsciiPixel('a'))))
    assert(img.toString == "a\n")
  }
  test("Picture with different length of rows") {
    try {
      val img = new AsciiImage(Array(Array(AsciiPixel('a')), Array(AsciiPixel('a'), AsciiPixel('b'))))
      assert(false)
    } catch {
      case _: Throwable => assert(true)
    }
  }
  test("Invalid image of size nx0") {
    try {
      val img = new AsciiImage(Array(Array(), Array()))
      assert(false)
    } catch {
      case _: Throwable => assert(true)
    }
  }
  test("Invalid image of size 0x0") {
    try {
      val img = new AsciiImage(Array())
      assert(false)
    } catch {
      case _: Throwable => assert(true)
    }
  }
}
