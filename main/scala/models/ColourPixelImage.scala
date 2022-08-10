package models

/**
 * Represents standard image consisting of RGB colour pixels
 * */
case class ColourPixelImage(override val pixels: Array[Array[ColourPixel]]) extends PixelImage[ColourPixel](pixels: Array[Array[ColourPixel]]) {
  def this(height: Int, width: Int) = {
    this(Array.ofDim[ColourPixel](height, width))
  }

  def equals(other: ColourPixelImage): Boolean = super.equals(other)
}
