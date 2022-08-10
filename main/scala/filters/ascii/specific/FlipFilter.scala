package filters.ascii.specific

import filters.ascii.AsciiFilter
import models.AsciiImage

/**
 * Filters ASCII image - flip image
 * */
case class FlipFilter(axis: Char) extends AsciiFilter {

  /**
   * Flip image according to axis
   * */
  override def filter(img: AsciiImage): AsciiImage = {

    val resImage = new AsciiImage(img.getHeight(), img.getWidth())
    for (i <- 0 until img.getHeight()) {
      for (j <- 0 until img.getWidth()) {
        resImage.setPixel(i, j, img.getPixelAt(getFlippedCoords(i, j, img)._1, getFlippedCoords(i, j, img)._2))
      }
    }
    resImage
  }

  /**
   * Get flipped coordinates of pixel
   * */
  protected def getFlippedCoords(i: Int, j: Int, img: AsciiImage): (Int, Int) = {
    axis match {
      case 'y' => (img.getHeight() - i - 1, j)
      case 'x' => (i, img.getWidth() - j - 1)
      case _ => throw new IllegalArgumentException("Flip filter does not support other axes then x, y")
    }
  }
}
