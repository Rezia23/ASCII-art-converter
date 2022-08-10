package filters

trait Filter[T] {
  def filter(item: T): T
}
