package fr.upem.course2

object _4_Curryfication {

  /**
    * Rappel:
    * Une fonction currifiée est une fonction a un argument (qui retourne éventuellement
    * d'autres fonctions a un argument)
    * Une fonction partiellement appliquée est une fonction dont un ou plusieurs arguments
    * ont été "fixés"
    */

  // 4.1 Currifiez la fonction add
  def add(a: Int, b: Int): Int = a + b
  // Créer une méthode currifiée
  def addCurriedMethod = ???
  // Créer une value function currifiée
  lazy val addCurriedValueFunction = ???
  // Créer une value function currifiée (en utilisant la méthode "curried" définie sur FunctionN)
  lazy val addCurriedStdLib = ???


  // 4.2 Appliquer partiellement la fonction addCurriedValueFunction précédente
  lazy val add5: Int => Int = ???

  // 4.3 Appliquer partiellement la fonction add précédente
  lazy val add3: Int => Int = ???

  // 4.4 Currifiez la fonction filter
  def filter[A](l: List[A], pred: A => Boolean): List[A] =
    l match {
      case Nil => Nil
      case h :: t if pred(h) => h :: filter(t, pred)
      case _ :: t => filter(t, pred)
    }
  def filterCurried[A]  = ???

  // 4.5 Appliquer partiellement la méthode filter (fixer le prédicat avec isEven)
  def isEven(i: Int): Boolean = i % 2 == 0
  lazy val keepEvenNumbers: List[Int] => List[Int] = ???

}
