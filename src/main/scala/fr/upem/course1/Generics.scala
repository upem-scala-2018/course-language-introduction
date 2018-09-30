package fr.upem.course1

object Generics {

  def identity[A]: A => A = ???

  def compose[A, B, C]: (A => B) => (B => C) => A => C = ???

  def total[A, B]: (A => B) => (A => Option[B]) = ???

  def currify[A, B, C]: ((A, B) => C) => A => B => C = ???

  def tupled[A, B, C]: (A => B => C) => (A, B) => C = ???

  def combine[A](xs: List[A]): A = ??? // Maybe you should add something to this method definition...

}
