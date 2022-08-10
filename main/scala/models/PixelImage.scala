package models

/**
 * Represents image consisting of pixels
 * */
abstract class PixelImage[T <: Pixel[T]](val pixels: Array[Array[T]]) extends Image {
  require(pixels.forall(row => row.length == pixels(0).length && pixels.length > 0 && pixels(0).length > 0), "Given array does not represent picture") //https://stackoverflow.com/questions/20478588/scala-case-class-how-to-validate-constructors-parameters

  def getPixelAt(x: Int, y: Int): T = pixels(x)(y)

  def setPixel(h: Int, w: Int, px: T): Unit = pixels(h)(w) = px

  def ==(other: PixelImage[T]): Boolean = equals(other)

  def equals(other: PixelImage[T]): Boolean = {
    if (getHeight() != other.getHeight() ||
      getWidth() != other.getWidth()) {
      return false
    }
    for (i <- 0 until getHeight()) {
      for (j <- 0 until getWidth()) {
        if (!pixels(i)(j).equals(other.pixels(i)(j))) {
          return false
        }
      }
    }
    true
  }

  override def getHeight(): Int = pixels.length

  override def getWidth(): Int = pixels(0).length
}
