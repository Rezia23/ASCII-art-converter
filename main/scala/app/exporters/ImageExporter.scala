package app.exporters

import exporters.Exporter
import models.Image


/**
 * Exports image
 * */
trait ImageExporter[T <: Image] extends Exporter[T] {

}
