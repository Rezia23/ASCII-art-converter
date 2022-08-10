package app.console.controllers

import app.exporters.ascii.AsciiStreamExporter
import convertors.ColourPixelToAsciiImageConvertor
import filters.ascii.AsciiFilter
import loaders.ColourPixelImageLoader

/**
 * Handles app logic
 * */
class ConsoleController extends Controller {
  /**
   * Load image, convert to ascii, export result to stream
   * */
  override def handleImageToAsciiStream(loader: ColourPixelImageLoader, exporter: AsciiStreamExporter, filter: AsciiFilter): Unit = {
    val img = loader.load
    val converter = new ColourPixelToAsciiImageConvertor()
    val converted = converter.convert(img)
    val res = filter.filter(converted)
    exporter.export(res)
    exporter.close()
  }
}
