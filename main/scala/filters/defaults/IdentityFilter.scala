package filters.defaults

import filters.Filter

class IdentityFilter[T] extends Filter[T] {
  override def filter(item: T): T = item
}
