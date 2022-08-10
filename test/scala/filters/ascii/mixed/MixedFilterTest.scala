package filters.ascii.mixed

import filters.ascii.specific.{FlipFilter, RotateFilter, ScaleFilter}
import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite

class MixedFilterTest extends FunSuite {
  test("Single filter - rotate 90") {
    val pixels = Array(Array('a', 'b'), Array('c', 'd'), Array('e', 'f'))
    val img = AsciiImage(pixels map (row => row map (v => AsciiPixel(v))))
    val flt = new MixedFilter(List(new RotateFilter(90)))
    val res = flt.filter(img)
    res match {
      case AsciiImage(Array(Array(AsciiPixel('e'), AsciiPixel('c'), AsciiPixel('a')), Array(AsciiPixel('f'), AsciiPixel('d'), AsciiPixel('b')))) => assert(true)
      case _ => assert(false)
    }
  }

  test("Multiple filters - rotate -90, flip x") {
    val pixels = Array(Array('a', 'b'), Array('c', 'd'), Array('e', 'f'))
    val img = AsciiImage(pixels map (row => row map (v => AsciiPixel(v))))
    val flt = new MixedFilter(List(new RotateFilter(-90), new FlipFilter('x')))
    val res = flt.filter(img)
    res match {
      case AsciiImage(Array(Array(AsciiPixel('f'), AsciiPixel('d'), AsciiPixel('b')), Array(AsciiPixel('e'), AsciiPixel('c'), AsciiPixel('a')))) => assert(true)
      case _ => assert(false)
    }
  }

  test("Multiple filters - rotate 180, flip y, scale 0.25") {
    val pixels = Array(Array('a', 'b'), Array('c', 'd'), Array('e', 'f'))
    val img = AsciiImage(pixels map (row => row map (v => AsciiPixel(v))))
    val flt = new MixedFilter(List(new RotateFilter(180), new FlipFilter('y'), new ScaleFilter(0.25)))
    val res = flt.filter(img)
    res match {
      case AsciiImage(Array(Array(AsciiPixel('b')), Array(AsciiPixel('f')))) => assert(true)
      case _ => assert(false)
    }
  }

  test("Multiple filters - flip x, flip y") {
    val pixels = Array(Array('a', 'b'), Array('c', 'd'), Array('e', 'f'))
    val img = AsciiImage(pixels map (row => row map (v => AsciiPixel(v))))
    val flt = new MixedFilter(List(new FlipFilter('x'), new FlipFilter('y')))
    val res = flt.filter(img)
    res match {
      case AsciiImage(Array(Array(AsciiPixel('f'), AsciiPixel('e')), Array(AsciiPixel('d'), AsciiPixel('c')), Array(AsciiPixel('b'), AsciiPixel('a')))) => assert(true)
      case _ => assert(false)
    }
  }

}
