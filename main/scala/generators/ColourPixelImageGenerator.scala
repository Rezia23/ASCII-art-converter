package generators

import models.{ColourPixel, ColourPixelImage}

import scala.util.Random

/**
 * Generates random ColourPixelImage within hard coded bounds
 * */
class ColourPixelImageGenerator extends ImageGenerator {

  /**
   * Generate random image of random size between lowerBound and upperBound
   * */
  private val lowerBound = 1
  private val upperBound = 20

  override def generate: ColourPixelImage = {
    val width = Random.between(lowerBound, upperBound)
    val height = Random.between(lowerBound, upperBound)
    val pixels = Array.ofDim[ColourPixel](height, width)
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        pixels(i)(j) = ColourPixel(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
      }
    }
    ColourPixelImage(pixels)
  }
}
