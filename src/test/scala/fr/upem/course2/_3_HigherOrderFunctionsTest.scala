package fr.upem.course2

import fr.upem.course2._3_HigherOrderFunctions.{compose, concatAsCsv, keepEvenNumbers}
import org.scalatest.{FlatSpec, Matchers}

class _3_HigherOrderFunctionsTest extends FlatSpec with Matchers {

  "3.1 KeepEventNumbers" should "filter odd" in {
    keepEvenNumbers(List(1, 2, 3, 4, 5)) should equal(List(2, 4))
  }

  "3.2 ConcatAsCsv" should "concat given elements to csv format" in {
    concatAsCsv(List(1, 2, 3), Integer.toString) should equal("1,2,3")
  }

  "3.3 ConcatAsCsv" should "concat given elements to csv format" in {
    val f : Int => String = Integer.toString
    val g : String => String = _ + " end"
    compose(f, g)(5) should equal("5 end")
  }

}
