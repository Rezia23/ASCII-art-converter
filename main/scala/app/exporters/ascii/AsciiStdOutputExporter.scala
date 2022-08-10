package app.exporters.ascii

/**
 * Exports ASCII image to standard output
 * */
class AsciiStdOutputExporter extends AsciiStreamExporter(System.out) {
  override def close(): Unit = {}
}
