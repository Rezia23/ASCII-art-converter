package loaders

import models.Image

trait ImageLoader {
  def load: Image
}
