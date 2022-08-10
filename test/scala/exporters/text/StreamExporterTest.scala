package exporters.text

import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream


class StreamExporterTest extends FunSuite {
  test("Write") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamExporter(stream)

    exporter.export("Ahoj")

    assert(stream.toString("UTF-8") == "Ahoj")
  }
  test("Closed stream") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamExporter(stream)
    exporter.close()
    try {
      exporter.export("Zdar")
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Close already closed stream") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamExporter(stream)
    exporter.close()
    exporter.close()
    try {
      exporter.export("Cus bus")
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
}

