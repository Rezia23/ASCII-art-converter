package loaders

import models.ColourPixelImage

/**
 * Loads ColourPixelImage
 * */
trait ColourPixelImageLoader extends ImageLoader {
  override def load: ColourPixelImage
}
