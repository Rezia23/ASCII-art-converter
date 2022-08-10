package app.console.views

import app.console.controllers.Controller
import app.exporters.ascii.{AsciiFileOutputExporter, AsciiStdOutputExporter, AsciiStreamExporter}
import filters.ascii.AsciiFilter
import filters.ascii.defaults.AsciiIdentityFilter
import filters.ascii.mixed.MixedFilter
import filters.ascii.specific.{FlipFilter, RotateFilter, ScaleFilter}
import loaders.file.ImageIOImageLoader
import loaders.random.RandomImageLoader
import loaders.{ColourPixelImageLoader, ImageLoader}

import java.io.File

/**
 * Handles user input and passing arguments to Controller
 */

class ConsoleView(args: Array[String], controller: Controller) {
  private val loaderRandomCmd = "--image-random"
  private val loaderFileCmd = "--image"
  private val exporterFileCmd = "--output-file"
  private val exporterConsoleCmd = "--output-console"
  private val filterScaleCmd = "--scale"
  private val filterRotateCmd = "--rotate"
  private val filterFlipCmd = "--flip"

  /**
   * Pass arguments as string to processCommand
   */
  def run(): Unit = {
    try {
      processCommand(args)
    } catch {
      case e: Exception => println("Command could not be processed, " + e.getMessage)
    }
  }

  /**
   * Parse command, pass created objects to Controller
   * */
  def processCommand(args: Array[String]): Unit = {
    var loader: ColourPixelImageLoader = null
    var exporter: AsciiStreamExporter = null
    var filters = List[AsciiFilter]()
    var i = 0
    while (i < args.length) {
      val currCommand = args(i)
      if (currCommand == loaderRandomCmd) { //parse random loader
        checkMultipleLoaderDefinition(loader)
        loader = new RandomImageLoader
      } else if (currCommand == loaderFileCmd) { //parse file loader
        checkMultipleLoaderDefinition(loader)
        i += 1
        checkIndexExistence(i, args.length, "No image input specified")
        val path = args(i)
        checkCorrectImageFormat(path)
        loader = new ImageIOImageLoader(path)
      } else if (currCommand == exporterConsoleCmd) { //parse console exporter
        checkMultipleExporterDefinition(exporter)
        exporter = new AsciiStdOutputExporter
      } else if (currCommand == exporterFileCmd) { //parse file exporter
        checkMultipleExporterDefinition(exporter)
        i += 1
        checkIndexExistence(i, args.length, "No image output specified")
        val path = args(i)
        exporter = new AsciiFileOutputExporter(new File(path))
      } else if (currCommand == filterScaleCmd) { //parse scale filter
        i += 1
        try {
          val scale = args(i).toDouble
          filters = filters.appended(new ScaleFilter(scale))
        } catch {
          case e: Exception => throw new IllegalArgumentException("Invalid argument type for " + filterScaleCmd)
        }
      } else if (currCommand == filterRotateCmd) { //parse rotate filter
        i += 1
        try {
          val deg = args(i).toInt
          filters = filters.appended(RotateFilter(deg))
        } catch {
          case e: Exception => throw new IllegalArgumentException("Invalid argument type for " + filterRotateCmd)
        }
      } else if (currCommand == filterFlipCmd) { //parse flip filter
        i += 1
        if (args(i).length != 1) {
          throw new IllegalArgumentException("Invalid type argument for " + filterFlipCmd)
        }
        val axis = args(i)(0)
        filters = filters.appended(new FlipFilter(axis))
      } else {
        throw new IllegalArgumentException("Invalid argument")
      }
      i += 1
    }
    if (filters.isEmpty) {
      filters = filters.appended(AsciiIdentityFilter)
    }
    controller.handleImageToAsciiStream(loader, exporter, new MixedFilter(filters))
  }

  protected def checkCorrectImageFormat(path: String): Unit = {
    val extensionReg = ".+\\.(?:png|jpg|jpeg)".r
    path match {
      case extensionReg() => //okay
      case _ => throw new Exception("Unsupported file format, supports only png, jpg, jpeg")
    }
  }

  protected def checkMultipleLoaderDefinition(loader: ImageLoader): Unit = {
    if (loader != null) {
      throw new Exception("Multiple loader arguments")
    }
  }

  protected def checkMultipleExporterDefinition(exporter: AsciiStreamExporter): Unit = {
    if (exporter != null) {
      throw new Exception("Multiple exporter arguments")
    }
  }

  protected def checkIndexExistence(index: Int, arrSize: Int, errorMsg: String): Unit = {
    if (index >= arrSize) {
      throw new Exception(errorMsg)
    }
  }
}
