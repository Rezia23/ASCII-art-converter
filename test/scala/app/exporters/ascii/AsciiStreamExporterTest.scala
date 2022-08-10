package app.exporters.ascii

import models.{AsciiImage, AsciiPixel}
import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class AsciiStreamExporterTest extends FunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val exporter = new AsciiStreamExporter(stream)
    val imgPx = Array.fill[Char](10, 10)('a')
    val img = AsciiImage(imgPx map (row => row map (v => new AsciiPixel(v))))
    exporter.export(img)
    assert(stream.toString("UTF-8") == img.toString)
  }

  test("Closed stream") {
    val stream = new ByteArrayOutputStream()
    val exporter = new AsciiStreamExporter(stream)
    exporter.close()
    val imgPx = Array.fill[Char](10, 10)('a')
    val img = AsciiImage(imgPx map (row => row map (v => new AsciiPixel(v))))
    try {
      exporter.export(img)
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }

  test("Close already closed stream") {
    val stream = new ByteArrayOutputStream()
    val exporter = new AsciiStreamExporter(stream)
    exporter.close()
    exporter.close()
    val imgPx = Array.fill[Char](10, 10)('a')
    val img = AsciiImage(imgPx map (row => row map (v => new AsciiPixel(v))))
    try {
      exporter.export(img)
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
}
