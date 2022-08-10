package app.console.views

import app.console.controllers.Controller
import app.exporters.ascii.{AsciiFileOutputExporter, AsciiStdOutputExporter, AsciiStreamExporter}
import filters.ascii.AsciiFilter
import filters.ascii.defaults.AsciiIdentityFilter
import filters.ascii.mixed.MixedFilter
import filters.ascii.specific.{FlipFilter, RotateFilter, ScaleFilter}
import loaders.ColourPixelImageLoader
import loaders.file.ImageIOImageLoader
import loaders.random.RandomImageLoader
import org.mockito.MockitoSugar.{mock, verify}
import org.mockito.captor.ArgCaptor
import org.scalatest.FunSuite

class ConsoleViewTest extends FunSuite {
  def cutStringToArgs(str: String): Array[String] = {
    (str.replaceAll(" +", " ").trim).split(" ")
  }
  //------------------------------------------------------------------------------processCommand tests
  test("Invalid command") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)

    try {
      view.processCommand(cutStringToArgs("something invalid"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }


  //-------------------------------wrong commands
  test("Wrong command, random image, no output") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-random"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong command, image from file, no output") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image tvojemama.png"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }

  test("Wrong command, no input") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--output-console"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong command, no path to image from file") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-file --output-console"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong command, non existent commands") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-random --output-console --uvar-mi-caj"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }

  test("Wrong command, unsupported file") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image cteSeTo.dzif --output-console"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong command, multiple loaders") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image file.png --output-console --image-random"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong command, multiple exporters") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--output-console --image file.jpg --output-console"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong command, not specified file after last argument --output-file") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-random --output-file"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }

  //-------------------------------correct commands
  test("load image from file, export to console") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)

    val path = "src/test/scala/testFiles/elmo.png"
    view.processCommand(cutStringToArgs("--image " + path + " --output-console"))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    capLoader.value match {
      case ImageIOImageLoader(path) => assert(true)
      case _ => assert(false)
    }
    assert(capExporter.value.isInstanceOf[AsciiStdOutputExporter])
    capFilter.value match {
      case MixedFilter(List(AsciiIdentityFilter)) => assert(true)
      case _ => assert(false)
    }
  }

  test("load random image, export to console") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    view.processCommand(cutStringToArgs("--image-random --output-console"))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    assert(capLoader.value.isInstanceOf[RandomImageLoader])
    assert(capExporter.value.isInstanceOf[AsciiStdOutputExporter])

    capFilter.value match {
      case MixedFilter(List(AsciiIdentityFilter)) => assert(true)
      case _ => assert(false)
    }
  }
  test("load random image, export to file") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    val outputPath = "idk.txt"
    view.processCommand(cutStringToArgs("--image-random --output-file " + outputPath))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    assert(capLoader.value.isInstanceOf[RandomImageLoader])
    assert(capExporter.value.isInstanceOf[AsciiFileOutputExporter])
    capFilter.value match {
      case MixedFilter(List(AsciiIdentityFilter)) => assert(true)
      case _ => assert(false)
    }
  }

  test("load image from file, export to file") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)

    val path = "src/test/scala/testFiles/elmo.png"
    val outPath = "idk.txt"
    view.processCommand(cutStringToArgs("--image " + path + " --output-file " + outPath))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    capLoader.value match {
      case ImageIOImageLoader(path) => assert(true)
      case _ => assert(false)
    }
    assert(capExporter.value.isInstanceOf[AsciiFileOutputExporter])
    capFilter.value match {
      case MixedFilter(List(AsciiIdentityFilter)) => assert(true)
      case _ => assert(false)
    }
  }

  //--------------------------------------- filter tests
  //wrong filter arguments
  test("Wrong scale filter argument") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-random --output-console --scale hodneMoc"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }
  test("Wrong rotate filter argument") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-random --output-console --rotate hodneMoc"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }

  test("Wrong flip filter argument") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.processCommand(cutStringToArgs("--image-random --output-console --flip 42"))
      assert(false)
    } catch {
      case e: Exception => assert(true)
    }
  }

  //correct filter arguments
  test("load random image, export to console, rotate filter") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    view.processCommand(cutStringToArgs("--image-random --output-console --rotate 90"))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    assert(capLoader.value.isInstanceOf[RandomImageLoader])
    assert(capExporter.value.isInstanceOf[AsciiStdOutputExporter])
    capFilter.value match {
      case MixedFilter(List(RotateFilter(90))) => assert(true)
      case _ => assert(false)
    }
  }
  test("load random image, export to console, multiple filters") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    view.processCommand(cutStringToArgs("--image-random --output-console --rotate 90 --scale 4 --flip x"))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    assert(capLoader.value.isInstanceOf[RandomImageLoader])
    assert(capExporter.value.isInstanceOf[AsciiStdOutputExporter])
    capFilter.value match {
      case MixedFilter(List(RotateFilter(90), ScaleFilter(4), FlipFilter('x'))) => assert(true)
      case _ => assert(false)
    }
  }

  test("load random image, export to console, multiple filters multiple times") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    view.processCommand(cutStringToArgs("--image-random --output-console --rotate 90 --scale 4 --flip x --rotate -90 --scale 0.25 --flip y"))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    assert(capLoader.value.isInstanceOf[RandomImageLoader])
    assert(capExporter.value.isInstanceOf[AsciiStdOutputExporter])
    capFilter.value match {
      case MixedFilter(List(RotateFilter(90), ScaleFilter(4), FlipFilter('x'), RotateFilter(-90), ScaleFilter(0.25), FlipFilter('y'))) => assert(true)
      case _ => assert(false)
    }
  }
  test("load random image, export to console, multiple filters multiple times, nonstandard order of arguments") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    view.processCommand(cutStringToArgs("--rotate 90 --scale 4 --flip x --image-random  --rotate -90 --scale 0.25 --output-console --flip y"))

    val capLoader = ArgCaptor[ColourPixelImageLoader]
    val capExporter = ArgCaptor[AsciiStreamExporter]
    val capFilter = ArgCaptor[AsciiFilter]
    verify(mockController).handleImageToAsciiStream(capLoader, capExporter, capFilter)

    assert(capLoader.value.isInstanceOf[RandomImageLoader])
    assert(capExporter.value.isInstanceOf[AsciiStdOutputExporter])
    capFilter.value match {
      case MixedFilter(List(RotateFilter(90), ScaleFilter(4), FlipFilter('x'), RotateFilter(-90), ScaleFilter(0.25), FlipFilter('y'))) => assert(true)
      case _ => assert(false)
    }
  }
  //---------------------------------------------------------------------------------------------------test run
  test("run does run") {
    val mockController = mock[Controller]
    val view = new ConsoleView(Array(""), mockController)
    try {
      view.run()
      assert(true)
    } catch {
      case _: Throwable => assert(false)
    }


  }
}
