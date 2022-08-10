package filters.ascii.defaults

import filters.ascii.AsciiFilter
import filters.defaults.IdentityFilter
import models.AsciiImage

/**
 * Filters ASCII image to itself
 * */
object AsciiIdentityFilter extends IdentityFilter[AsciiImage] with AsciiFilter {
  override def filter(img: AsciiImage): AsciiImage = img
}
