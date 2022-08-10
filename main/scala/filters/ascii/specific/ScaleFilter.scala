package filters.ascii.specific

import filters.ascii.AsciiFilter
import models.AsciiImage

/**
 * Filters ASCII image - scale image
 * */
case class ScaleFilter(scale: Double) extends AsciiFilter {

  /**
   * Create scaled image
   * */
  override def filter(img: AsciiImage): AsciiImage = {
    scale match {
      case 1 => img
      case 0.25 => filterSmaller(img, 2)
      case 4 => filterBigger(img, 2)
      case _ => throw new IllegalArgumentException("Scale filter supports only scales 1, 0.25, 4")
    }

  }

  /**
   * Scale image to smaller size according to resizeVal
   * */
  def filterSmaller(img: AsciiImage, resizeVal: Int): AsciiImage = {
    val filteredHeight = (img.getHeight() / resizeVal).toInt + img.getHeight() % resizeVal
    val filteredWidth = (img.getWidth() / resizeVal).toInt + img.getWidth() % resizeVal
    val resImage = new AsciiImage(filteredHeight, filteredWidth)
    for (i <- 0 until filteredHeight) {
      for (j <- 0 until filteredWidth) {
        resImage.setPixel(i, j, img.getPixelAt(i * resizeVal, j * resizeVal))
      }
    }
    resImage
  }

  /**
   * Scale image to bigger size according to resizeVal
   * */
  def filterBigger(img: AsciiImage, resizeVal: Int): AsciiImage = {
    var resImage = new AsciiImage(img.getHeight() * resizeVal, img.getWidth() * resizeVal)
    for (i <- 0 until img.getHeight()) {
      for (j <- 0 until img.getWidth()) {
        resImage = expandOnePixel(resImage, img, i, j, resizeVal)
      }
    }
    resImage
  }

  protected def expandOnePixel(resImage: AsciiImage, img: AsciiImage,
                               origHeight: Int, origWidth: Int, resizeVal: Int): AsciiImage = {
    for (i <- 0 until resizeVal) {
      for (j <- 0 until resizeVal) {
        resImage.setPixel(resizeVal * origHeight + i, resizeVal * origWidth + j, img.getPixelAt(origHeight, origWidth))
      }
    }
    resImage
  }
}
