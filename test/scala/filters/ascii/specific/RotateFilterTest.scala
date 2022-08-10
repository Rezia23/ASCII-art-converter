package filters.ascii.specific

import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite


class RotateFilterTest extends FunSuite {
  test("Rotate 0 small img") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new RotateFilter(0)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f')))))
  }

  test("Rotate 90 small img") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d'))))
    val flt = new RotateFilter(90)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('c'), AsciiPixel('a')), Array(AsciiPixel('d'), AsciiPixel('b')))))
  }

  test("Rotate 180 small img") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d'))))
    val flt = new RotateFilter(180)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('d'), AsciiPixel('c')), Array(AsciiPixel('b'), AsciiPixel('a')))))
  }

  test("Rotate -90 non square") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new RotateFilter(-90)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('b'), AsciiPixel('d'), AsciiPixel('f')), Array(AsciiPixel('a'), AsciiPixel('c'), AsciiPixel('e')))))
  }

  test("Rotate -540 (180) non square") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new RotateFilter(-540)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('f'), AsciiPixel('e')), Array(AsciiPixel('d'), AsciiPixel('c')), Array(AsciiPixel('b'), AsciiPixel('a')))))
  }

  test("Rotate non multiple of 90") {

    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new RotateFilter(91)
    try {
      flt.filter(img)
      assert(false)
    } catch {
      case e: IllegalArgumentException => assert(true)
      case _: Throwable => assert(false)
    }
  }
}
