package app.exporters.ascii

import helpers.TestWithFiles
import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite

import java.io.File

/*
 * Used code is adapted from BI-OOP lab.
 */

class AsciiFileOutputExporterTest extends FunSuite
  with TestWithFiles {

  test("No file exists") {
    val fileName = getTestFile

    try {
      ensureDeleted(fileName)

      val file = new File(fileName)
      val exporter = new AsciiFileOutputExporter(file)
      val imgPx = Array(Array('a', 'h', 'o', 'j'),
        Array('c', 'u', 's', '!'))
      val img = AsciiImage(imgPx map (row => row map (v => new AsciiPixel(v))))
      exporter.export(img)
      exporter.close()


      assertFileContent(fileName, img.toString)

    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("File already exists") {
    val fileName = getTestFile

    try {
      ensureCreated(fileName)

      val file = new File(fileName)
      val exporter = new AsciiFileOutputExporter(file)
      val imgPx = Array(Array('z', 'd', 'a', 'r', 'e', 'k'),
        Array('p', 'a', 'r', 'e', 'k', '!'))
      val img = AsciiImage(imgPx map (row => row map (v => new AsciiPixel(v))))
      exporter.export(img)
      exporter.close()
      assertFileContent(fileName, img.toString)
    }
    finally {
      ensureDeleted(fileName)
    }
  }
}
