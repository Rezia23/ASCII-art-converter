package models

/**
 * Represents one pixel of ASCII image
 * */
case class AsciiPixel(symbol: Char) extends Pixel[AsciiPixel] {
  override def equals(other: AsciiPixel): Boolean = getSymbol == other.getSymbol

  def getSymbol: Char = symbol

  override def toString: String = symbol.toString
}
