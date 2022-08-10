package models

/**
 * Represents one RGB pixel of ColourPixelImage
 * */
case class ColourPixel(red: Int, green: Int, blue: Int) extends Pixel[ColourPixel] {
  override def equals(other: ColourPixel): Boolean = {
    getRed == other.getRed && getGreen == other.getGreen && getBlue == other.getBlue
  }

  def getRed: Int = red

  def getGreen: Int = green

  def getBlue: Int = blue

}
