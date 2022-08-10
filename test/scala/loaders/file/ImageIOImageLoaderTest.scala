package loaders.file

import models.{ColourPixel, ColourPixelImage}
import org.scalatest.FunSuite

import java.awt.Color
import java.io.{File, IOException}
import javax.imageio.ImageIO


class ImageIOImageLoaderTest extends FunSuite {
  def loadAndCheckBasicImage(path: String): Unit = {
    val loader = new ImageIOImageLoader(path)
    val Img = loader.load

    val img = ImageIO.read(new File(path))
    var pixels = Array.ofDim[ColourPixel](img.getHeight, img.getWidth())

    for (i <- 0 until img.getHeight()) {
      for (j <- 0 until img.getWidth) {
        val color = new Color(img.getRGB(j, i), true)
        pixels(i)(j) = new ColourPixel(color.getRed, color.getGreen, color.getBlue)
      }
    }
    assert(Img == new ColourPixelImage(pixels))
  }

  test("Load jpeg") {
    val path = "src/test/scala/testFiles/harold.jpeg"
    loadAndCheckBasicImage(path)


  }
  test("Load png") {
    val path = "src/test/scala/testFiles/spongebob.png"
    loadAndCheckBasicImage(path)
  }
  test("Copy of same picture loads the same") {
    val path1 = "src/test/scala/testFiles/spongebob.png"
    val path2 = "src/test/scala/testFiles/spongebobCopy.png"
    val loader1 = ImageIOImageLoader(path1)
    val loader2 = ImageIOImageLoader(path2)
    assert(loader1.load == (loader2.load))
  }

  test("Non existing image") {
    val path = "non/existent/file.jpg"
    val loader = ImageIOImageLoader(path)
    try {
      loader.load
      assert(false)
    } catch {
      case e: IOException => assert(true)
      case _: Throwable => assert(false)
    }
  }

  test("Wrong file format") {
    val path = "src/test/scala/testFiles/kermit.txt"
    val loader = ImageIOImageLoader(path)
    try {
      loader.load
      assert(false)
    } catch {
      case e: IOException => assert(true)
      case _: Throwable => assert(false)
    }
  }

  test("Load 1x1 image") {
    val path = "src/test/scala/testFiles/size1x1.png"
    loadAndCheckBasicImage(path)
  }


  //  //absolute path - works only on my computer
  //  test("Load image, absolute path"){
  //    val path = "/home/terez/Documents/FIT/OOP/asciiart/src/test/scala/testFiles/spongebob.png"
  //    loadAndCheckBasicImage(path)
  //  }
}
