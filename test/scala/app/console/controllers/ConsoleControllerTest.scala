package app.console.controllers

import app.exporters.ascii.AsciiStreamExporter
import filters.ascii.defaults.AsciiIdentityFilter
import loaders.random.RandomImageLoader
import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class ConsoleControllerTest extends FunSuite {
  test("Handle image to ascii stream method") {
    val mockLoader = new RandomImageLoader
    val mockFilter = AsciiIdentityFilter

    val stream = new ByteArrayOutputStream()
    val mockExporter = new AsciiStreamExporter(stream)

    try {
      val controller = new ConsoleController
      controller.handleImageToAsciiStream(mockLoader, mockExporter, mockFilter)
      assert(true)
    } catch {
      case _: Throwable => assert(false)
    }
  }
}
