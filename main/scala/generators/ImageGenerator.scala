package generators

import models.Image

/**
 * Generates image
 * */
trait ImageGenerator {
  def generate: Image
}
