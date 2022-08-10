package filters.ascii.mixed

import filters.ascii.AsciiFilter
import models.AsciiImage

/**
 * Filters ASCII image with multiple specific filters
 * */
case class MixedFilter(filters: Seq[AsciiFilter]) extends AsciiFilter {
  /**
   * Apply all filters
   * */
  override def filter(item: AsciiImage): AsciiImage =
    filters.foldLeft(item)((accumulator, fil) => fil.filter(accumulator))

}
