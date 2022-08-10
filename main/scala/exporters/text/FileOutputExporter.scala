package exporters.text

import java.io.{File, FileOutputStream}

/**
 * Exports text to file
 * */
class FileOutputExporter(file: File)
  extends StreamExporter(new FileOutputStream(file)) {

}
