package fr.upem.course2

import org.scalatest.{FlatSpec, Matchers}
import _4_Curryfication._

class _4_CurryficationTest extends FlatSpec with Matchers {

  "4.1 AddCurriedMethod" should "add given arguments" in {
    "addCurriedMethod(1)(2)" should compile
  }

  "4.1 AddCurriedValueFunction" should "compile" in {
    "addCurriedValueFunction(1)(2)" should compile
  }

  "4.1 AddCurriedStdLib" should "compile" in {
    "addCurriedStdLib(1)(2)" should compile
  }

  "4.2 Add5" should "add 5 to given argument" in {
    add5(2) should equal(7)
  }

  "4.3 Add3" should "add 3 to given argument" in {
    add3(2) should equal(5)
  }

  "4.4 FilterCurried" should "filter elements satisfying given predicate" in {
    "filterCurried(List(1, 2))(_ == 1)" should compile
  }

  "4.5 KeepEvenNumbers" should "keep even numbers of given list" in {
    keepEvenNumbers(List(1, 2, 3, 4)) should equal(List(2, 4))
  }

}
