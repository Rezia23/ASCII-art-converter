package convertors

import convertors.asciiscale.AsciiScale
import models.{AsciiImage, AsciiPixel, ColourPixel, ColourPixelImage}
import org.scalatest.FunSuite


class ColourPixelToAsciiImageConvertorTest extends FunSuite {

  test("Convert 2x2 black") {
    val px: Array[Array[ColourPixel]] = Array(Array(new ColourPixel(0, 0, 0), new ColourPixel(0, 0, 0))
      , Array(new ColourPixel(0, 0, 0), new ColourPixel(0, 0, 0)))
    val img = new ColourPixelImage(px)

    val convertor = new ColourPixelToAsciiImageConvertor()
    val converted = convertor.convert(img)

    assert(converted == AsciiImage(Array(Array(AsciiPixel('$'), AsciiPixel('$')),
      Array(AsciiPixel('$'), AsciiPixel('$')))))
  }

  test("Convert 2x3 white") {
    val px: Array[Array[ColourPixel]] = Array(Array(new ColourPixel(255, 255, 255), new ColourPixel(255, 255, 255), new ColourPixel(255, 255, 255))
      , Array(new ColourPixel(255, 255, 255), new ColourPixel(255, 255, 255), new ColourPixel(255, 255, 255)))
    val img = new ColourPixelImage(px)

    val convertor = new ColourPixelToAsciiImageConvertor()
    val converted = convertor.convert(img)

    assert(converted == AsciiImage(Array(Array(AsciiPixel(';'), AsciiPixel(';'), AsciiPixel(';')),
      Array(AsciiPixel(';'), AsciiPixel(';'), AsciiPixel(';')))))
  }

  test("Convert 2x3, check colour converts the same twice") {
    val px: Array[Array[ColourPixel]] = Array(Array(new ColourPixel(7, 200, 255), new ColourPixel(1, 0, 76), new ColourPixel(92, 42, 24))
      , Array(new ColourPixel(7, 200, 255), new ColourPixel(1, 0, 76), new ColourPixel(92, 42, 24)))
    val img = ColourPixelImage(px)

    val convertor = new ColourPixelToAsciiImageConvertor()
    val converted = convertor.convert(img)
    assert(
      converted.getPixelAt(0, 0) == converted.getPixelAt(1, 0) &&
        converted.getPixelAt(0, 1) == converted.getPixelAt(1, 1) &&
        converted.getPixelAt(0, 2) == converted.getPixelAt(1, 2)
    )
  }

  test("Convert colourful image") {
    val px = Array(Array(new ColourPixel(4, 26, 159), new ColourPixel(78, 1, 12)),
      Array(new ColourPixel(11, 6, 19), new ColourPixel(178, 255, 4)))
    val img = ColourPixelImage(px)
    val convertor = new ColourPixelToAsciiImageConvertor
    val converted = convertor.convert(img)
    for (row <- 0 until converted.getHeight()) {
      for (col <- 0 until converted.getWidth()) {
        assert(converted.getPixelAt(row, col).getSymbol == AsciiScale.getChar(PixelToGrayscaleConvertor.convert(px(row)(col))))
      }
    }
  }
}