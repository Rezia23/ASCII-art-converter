package loaders.random

import generators.ColourPixelImageGenerator
import loaders.ColourPixelImageLoader
import models.ColourPixelImage

/**
 * Loads random ColourPixelImage
 * */
class RandomImageLoader extends ColourPixelImageLoader {
  override def load: ColourPixelImage = new ColourPixelImageGenerator().generate
}
