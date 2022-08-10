package app.console.controllers

import app.exporters.ascii.AsciiStreamExporter
import filters.ascii.AsciiFilter
import loaders.ColourPixelImageLoader

/**
 * Handles app logic
 * */
trait Controller {

  /**
   * Load image, convert to ascii, export result to stream
   * */
  def handleImageToAsciiStream(loader: ColourPixelImageLoader, exporter: AsciiStreamExporter, filter: AsciiFilter): Unit
}
