package fr.upem.course1

import org.scalatest.{FlatSpec, Matchers}

class GenericsSpec extends FlatSpec with Matchers {

  "Generic identity" should "returns itself" in {
    Generics.identity(true) should be(true)
    Generics.identity(8) should be(8)
    Generics.identity("Hey") should be("Hey")
  }

  "Generic compose" should "compose two functions" in {
    val plusTen = (x: Int) => x + 10
    val toInt = (s: String) => s.toInt

    Generics.compose(toInt)(plusTen)("3") should be(13)
  }

  it should "compose two other functions" in {
    def toSome[A] = (x: A) => Some(x)

    val toUpper = (s: String) => s.toUpperCase

    Generics.compose(toUpper)(toSome)("Hello") should be(Some("HELLO"))
  }

  "Generic total" should "returns some if is defined" in {
    val toInt = (s: String) => s.toInt

    Generics.total(toInt)("7") should be(Some(7))
  }

  it should "returns none if is not defined" in {
    val toInt = (s: String) => s.toInt

    Generics.total(toInt)("Hello") should be(None)
  }

  "Generic currify" should "currify a function" in {
    val f = (x: Int, y: Int) => x + y

    Generics.currify(f)(1)(5) should be(6)
  }

  "Generic tupled" should "tuple a function" in {
    val f: Int => Int => Int = x => y => x + y

    Generics.tupled(f)(1, 5) should be(6)
  }

  "Generic combine" should "sum integers" in {
    Generics.combine(1 :: 2 :: 3 :: 4 :: 5 :: Nil) should be(15)
  }

  it should "concatenate strings" in {
    Generics.combine("H" :: "e" :: "l" :: "l" :: "o" :: Nil) should be("Hello")
  }

  it should "'and' operator booleans" in {
    Generics.combine(true :: true :: true :: Nil) should be(true)
    Generics.combine(true :: false :: true :: Nil) should be(false)
  }

}
