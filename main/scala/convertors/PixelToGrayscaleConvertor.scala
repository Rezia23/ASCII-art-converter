package convertors

import models.ColourPixel

/**
 * Converts ColourPixel to its grayscale value
 * */
object PixelToGrayscaleConvertor {
  def convert(px: ColourPixel): Int = {
    (px.getRed * 0.3 + px.getGreen * 0.59 + px.getBlue * 0.11).toInt
  }
}
