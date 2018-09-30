package fr.upem.course1

import scala.util.{Failure, Success, Try}

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

  def parseInt(s: String): Option[Int] =
    Try(s.toInt).toOption

  def getAtIndex[A](n: Int)(xs: List[A]): Try[A] =
    xs
      .lift(n)
      .map(Success(_))
      .getOrElse(Failure(NoElementAtIndex(n)))

  def divide(x: Int, y: Int): Either[DivideByZero.type , Int] =
    if (y == 0)
      Left(DivideByZero)
    else
      Right(x / y)

  def getMemberById(id: Member.Id): Option[Member] =
    Exceptions.dataset.get(id)

}
