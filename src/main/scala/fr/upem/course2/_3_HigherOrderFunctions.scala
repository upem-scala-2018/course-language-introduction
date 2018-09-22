package fr.upem.course2

object _3_HigherOrderFunctions {

  /**
    * La librairie standard utilise abandonment les fonctions d'ordre supérieur.
    * Rappel: Une fonction d'ordre supérieur est une fonction qui prend une fonction
    * d'ordre supérieur en argument ou retourne une fonction.
    */

  // 3.1 Trouvez une fonction d'ordre supérieur de la librairie standard qui conserve les nombre pairs.
  def keepEvenNumbers[A](l: List[Int]): List[Int] = ???

  // 3.2 Concaténer la liste au format csv (ex: List("a","b") => a,b)
  def concatAsCsv[A](l: List[A], show: A => String): String = ???

  // 3.3 La fonction compose prend deux fonctions en argument et retourne une fonction
  // qui est la composition des deux.
  // Exemple :
  // val add1: Int => Int = i => i + 1
  // val add2 = compose(add1, add1)
  def compose[A, B, C](f: A => B, g: B => C): A => C = f.andThen(g)


}
