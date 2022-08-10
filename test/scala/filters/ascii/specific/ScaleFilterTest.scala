package filters.ascii.specific

import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite

class ScaleFilterTest extends FunSuite {
  def getImageFromArr(arr: Array[Array[Char]]): AsciiImage = {
    val imgPx = arr map {
      row =>
        row map {
          px => new AsciiPixel(px)
        }
    }
    AsciiImage(imgPx)
  }

  test("scale 1") {
    val img = getImageFromArr(Array(Array('a', 'b'), Array('c', 'd')))
    val flt = new ScaleFilter(1)
    val res = flt.filter(img)
    val refImg = getImageFromArr(Array(Array('a', 'b'), Array('c', 'd')))
    val wrongImg = getImageFromArr(Array(Array('a', 'd'), Array('c', 'd')))
    assert(refImg == AsciiImage(
      Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')))))
  }

  test("scale 4") {
    val img = getImageFromArr(Array(Array('a', 'b'), Array('c', 'd')))
    val flt = new ScaleFilter(4)
    val res = flt.filter(img)
    assert(res == AsciiImage(
      Array(Array(AsciiPixel('a'), AsciiPixel('a'), AsciiPixel('b'), AsciiPixel('b')),
        Array(AsciiPixel('a'), AsciiPixel('a'), AsciiPixel('b'), AsciiPixel('b')),
        Array(AsciiPixel('c'), AsciiPixel('c'), AsciiPixel('d'), AsciiPixel('d')),
        Array(AsciiPixel('c'), AsciiPixel('c'), AsciiPixel('d'), AsciiPixel('d')))))
  }

  test("scale 0.25 small image") {
    val img = getImageFromArr(Array(Array('a', 'b'), Array('c', 'd')))
    val flt = new ScaleFilter(0.25)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('a')))))
  }

  test("scale 0.25 bigger image") {
    val img = AsciiImage(
      Array(Array(AsciiPixel('a'), AsciiPixel('a'), AsciiPixel('b'), AsciiPixel('b')),
        Array(AsciiPixel('a'), AsciiPixel('x'), AsciiPixel('b'), AsciiPixel('b')),
        Array(AsciiPixel('c'), AsciiPixel('d'), AsciiPixel('d'), AsciiPixel('d')),
        Array(AsciiPixel('c'), AsciiPixel('c'), AsciiPixel('d'), AsciiPixel('w'))))
    val flt = new ScaleFilter(0.25)
    val res = flt.filter(img)
    assert(res == AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')))))
  }

  test("Scale non supported value") {

    val img = AsciiImage(Array(Array(AsciiPixel('a'), AsciiPixel('b')), Array(AsciiPixel('c'), AsciiPixel('d')), Array(AsciiPixel('e'), AsciiPixel('f'))))
    val flt = new ScaleFilter(42)
    try {
      flt.filter(img)
      assert(false)
    } catch {
      case e: IllegalArgumentException => assert(true)
      case _: Throwable => assert(false)
    }
  }
}
