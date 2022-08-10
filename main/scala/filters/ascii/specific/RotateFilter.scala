package filters.ascii.specific

import filters.ascii.AsciiFilter
import models.AsciiImage

/**
 * Filters ASCII image - rotate image
 * */
case class RotateFilter(deg: Int) extends AsciiFilter {
  val baseRotationDegrees = 90

  /**
   * Create image rotated deg degrees
   * */
  override def filter(img: AsciiImage): AsciiImage = {
    if (deg % baseRotationDegrees != 0) {
      throw new IllegalArgumentException("Rotation possible only in multiples of 90 degrees")
    }
    //convert degrees to positive value < 360
    val realDeg = if (deg < 0) 360 + (deg % 360) else (deg % 360)

    var currImage = img
    for (m <- 0 until realDeg / baseRotationDegrees) {
      val resImage = new AsciiImage(currImage.getWidth(), currImage.getHeight())
      for (i <- 0 until currImage.getWidth()) {
        for (j <- 0 until currImage.getHeight()) {
          resImage.setPixel(i, j, currImage.getPixelAt(currImage.getHeight() - j - 1, i))
        }
      }
      currImage = resImage
    }
    currImage
  }
}
