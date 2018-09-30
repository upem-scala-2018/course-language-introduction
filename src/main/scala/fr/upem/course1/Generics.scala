package fr.upem.course1

import scala.util.Try

object Generics {

  def identity[A]: A => A = a => a

  def compose[A, B, C]: (A => B) => (B => C) => A => C = f => g => f.andThen(g)

  def total[A, B]: (A => B) => (A => Option[B]) = f => a => Try(f(a)).toOption

  def currify[A, B, C]: ((A, B) => C) => A => B => C = f => a => b => f(a, b)

  def tupled[A, B, C]: (A => B => C) => (A, B) => C = f => (a, b) => f(a)(b)

  def combine[A](xs: List[A]): A = ???

}
