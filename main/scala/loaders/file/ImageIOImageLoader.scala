package loaders.file

import loaders.ColourPixelImageLoader
import models.{ColourPixel, ColourPixelImage}

import java.awt.Color
import java.io.{File, IOException}
import javax.imageio.ImageIO

/**
 * Loads from file image formats supported by ImageIO
 * */
case class ImageIOImageLoader(path: String) extends FileImageLoader with ColourPixelImageLoader {
  /**
   * Load image from file, return ColourPixelImage representation of it
   * Load only file types supported by ImageIO
   * */

  override def load: ColourPixelImage = {
    val img = ImageIO.read(new File(path))
    if (img == null) {
      throw new IOException("Unsupported file format")
    }

    val resImage = new ColourPixelImage(img.getHeight, img.getWidth)
    val intPixels = Array.ofDim[Int](img.getHeight, img.getWidth())

    for (i <- 0 until img.getHeight()) {
      img.getRGB(0, i, img.getWidth, 1, intPixels(i), 0, img.getWidth)
    }
    for (i <- 0 until img.getHeight()) {
      for (j <- 0 until img.getWidth()) {
        val color = new Color(intPixels(i)(j), true)
        resImage.setPixel(i, j, new ColourPixel(color.getRed, color.getGreen, color.getBlue))
      }
    }
    resImage
  }
}
