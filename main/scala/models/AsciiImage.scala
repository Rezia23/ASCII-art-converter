package models

import scala.collection.mutable

/**
 * Represents ASCII image
 * */
case class AsciiImage(override val pixels: Array[Array[AsciiPixel]]) extends PixelImage[AsciiPixel](pixels: Array[Array[AsciiPixel]]) {
  def this(height: Int, width: Int) = {
    this(Array.ofDim[AsciiPixel](height, width))
  }

  def equals(other: AsciiImage): Boolean = super.equals(other)

  /**
   * Converts image to string, rows separated by newline
   * https://www.geeksforgeeks.org/stringbuilder-in-scala/
   * */
  override def toString: String = {
    val str = new StringBuilder()
    for (i <- 0 until getHeight()) {
      for (j <- 0 until getWidth()) {
        str.append(pixels(i)( j))
      }
      str.append('\n')
    }
    str.toString()
  }
}
