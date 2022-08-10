package convertors

import models.Image

/**
 * Converts to image
 * */
trait ImageConvertor[T] {
  def convert(image: T): Image
}
