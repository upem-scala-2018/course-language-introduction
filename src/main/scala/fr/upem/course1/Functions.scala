package fr.upem.course1

object Functions {

  val sum: (Int, Int) => Int = (a, b) =>
    a + b

  val sumAll: List[Int] => Int = {
    case h :: t => h + sumAll(t)
    case Nil => 0
  }

  val concat: (String, String) => String = (a, b) =>
    a + b

}
