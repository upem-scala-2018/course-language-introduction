package fr.upem.course1

object Implicits {

  final case class Id(value: String) extends AnyVal

  implicit class StringOps(s: String) {
    def some: Option[String] = Some(s)
    def isTrue: Boolean = s == "true"
    def id: Id = Id(s)
  }

  implicit class IntOps(i: Int) {
    def some: Option[Int] = Some(i)
  }

  implicit class BooleanOps(b: Boolean) {
    def some: Option[Boolean] = Some(b)
  }

}
