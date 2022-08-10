package filters.ascii.defaults

import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite

class IdentityFilterTest extends FunSuite {
  test("Identity test") {
    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))

    val res = AsciiIdentityFilter.filter(img)
    res match {
      case AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f')))) => assert(true)
      case _ => assert(false)
    }
  }

}
