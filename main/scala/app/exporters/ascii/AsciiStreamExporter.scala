package app.exporters.ascii

import exporters.text.StreamExporter
import models.AsciiImage

import java.io.OutputStream

/**
 * Exports ASCII image to stream
 * */

class AsciiStreamExporter(outputStream: OutputStream) extends AsciiImageExporter {
  private var closed = false

  /**
   * Export input AsciiImage to outputStream
   * */
  override def export(item: AsciiImage): Unit = {
    if (closed) {
      throw new Exception("The stream is already closed")
    }
    val stringExporter = new StreamExporter(outputStream)
    stringExporter.export(item.toString)
  }

  /**
   * Close stream
   * */
  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }
}
