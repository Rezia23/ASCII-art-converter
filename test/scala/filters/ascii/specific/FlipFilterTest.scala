package filters.ascii.specific

import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite

class FlipFilterTest extends FunSuite {
  test("Flip x") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d'))))
    val flt = new FlipFilter('x')
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('b'), AsciiPixel('a')), Array(AsciiPixel('d'), AsciiPixel('c')))))
  }
  test("Flip y") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d'))))
    val flt = new FlipFilter('y')
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('a'), AsciiPixel('b')))))
  }

  test("Flip x non square") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new FlipFilter('x')
    val res = flt.filter(img)

    assert(res == AsciiImage(Array(Array(AsciiPixel('b'), AsciiPixel('a')), Array(AsciiPixel('d'), AsciiPixel('c')), Array(AsciiPixel('f'), AsciiPixel('e')))))
  }

  test("Flip y non square") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new FlipFilter('y')
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('e'), AsciiPixel('f')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('a'), AsciiPixel('b')))))
  }

  test("Flip non existing axis") {

    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new FlipFilter('f')
    try {
      flt.filter(img)
      assert(false)
    } catch {
      case e: IllegalArgumentException => assert(true)
      case _: Throwable => assert(false)
    }
  }


}
