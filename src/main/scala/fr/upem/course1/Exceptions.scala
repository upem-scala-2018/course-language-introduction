package fr.upem.course1

object Exceptions {

  final case class NoElementAtIndex(n: Int) extends Exception

  final case object DivideByZero

  final case class Member(id: Member.Id, name: String)

  object Member {

    final case class Id(value: String) extends AnyVal

  }

  val dataset: Map[Member.Id, Member] = Map(
    Member.Id("1") -> Member(Member.Id("1"), "Charles"),
    Member.Id("2") -> Member(Member.Id("2"), "Maria"),
    Member.Id("3") -> Member(Member.Id("3"), "Simon"),
    Member.Id("4") -> Member(Member.Id("4"), "Julie"),
    Member.Id("5") -> Member(Member.Id("5"), "Sarah")
  )

  def parseInt(s: String): Int = ??? // Update signature to handle error

  def getAtIndex[A](n: Int)(xs: List[A]): A = ??? // Update signature to handle error

  def divide(x: Int, y: Int): Int = ??? // Update signature to handle error

  def getMemberById(id: Member.Id): Member = ??? // Update signature to handle error and writes some unit tests ;)
}
