package app.exporters.ascii

import java.io.{File, FileOutputStream}

/**
 * Exports ASCII image to file
 * */
class AsciiFileOutputExporter(file: File)
  extends AsciiStreamExporter(new FileOutputStream(file)) {

}
