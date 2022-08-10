package convertors

import convertors.asciiscale.AsciiScale
import models.{AsciiImage, AsciiPixel, ColourPixel, ColourPixelImage}

/**
 * Converts ColourPixelImage to AsciiImage
 * */
class ColourPixelToAsciiImageConvertor extends ImageConvertor[ColourPixelImage] {
  /**
   * Convert ColourPixelImage to AsciiImage
   * */
  override def convert(image: ColourPixelImage): AsciiImage = {
    val asciiImg = new AsciiImage(image.getHeight(), image.getWidth())
    for (i <- 0 until image.getHeight()) {
      for (j <- 0 until image.getWidth()) {
        asciiImg.setPixel(i, j, convertPixelToAsciixel(image.getPixelAt(i, j)))
      }
    }
    asciiImg
  }

  /**
   * Return corresponding AsciiPixel for given ColourPixel
   * */
  def convertPixelToAsciixel(pixel: ColourPixel): AsciiPixel = {
    AsciiPixel(AsciiScale.getChar(PixelToGrayscaleConvertor.convert(pixel)))
  }
}
