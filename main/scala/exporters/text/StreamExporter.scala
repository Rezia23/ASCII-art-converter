package exporters.text


import java.io.OutputStream

/*
* Code is adopted from BI-OOP lab.
* */

/**
 * Exports text to stream
 * */
class StreamExporter(outputStream: OutputStream) extends TextExporter {
  private var closed = false

  /**
   * Close stream
   * */
  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def export(item: String): Unit = exportToStream(item)

  protected def exportToStream(text: String): Unit = {

    if (closed)
      throw new Exception("The stream is already closed")

    outputStream.write(text.getBytes("UTF-8"))
    outputStream.flush()
  }
}
