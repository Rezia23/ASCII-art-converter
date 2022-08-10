package models


abstract class Pixel[T <: Pixel[T]] {
  def equals(other: T): Boolean

  def ==(other: T): Boolean = equals(other)

}
