package fr.upem.course1

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import scala.util.{Failure, Success}

class ExceptionsSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "Errors parseInt" should "parse an int as a string" in {
    forAll { n: Int =>
      Exceptions.parseInt(n.toString) should be(Some(n))
    }
  }

  it should "returns an option if the string is not an integer" in {
    forAll { s: String =>
      Exceptions.parseInt(s) should be(None)
    }
  }

  "Errors getAtIndex" should "successfully get an element at index" in {
    forAll { (xs: List[String], n: Int) =>
      val index = n % xs.length
      Exceptions.getAtIndex(index)(xs) should be(Success(xs(index)))
    }
  }

  it should "returns an error if the index does not exist" in {
    forAll { (xs: List[String], n: Int) =>
      val index = xs.length + n
      Exceptions.getAtIndex(index)(xs) should be(Failure(Exceptions.NoElementAtIndex(index)))
    }
  }

  "Errors divide" should "successfully divide two integers" in {
    forAll { n: Int =>
      Exceptions.divide(n, 2) should be(Right(n / 2))
    }
  }

  "Errors divide" should "successfully divide two integers" in {
    forAll { n: Int =>
      Exceptions.divide(n, 0) should be(Left(Exceptions.DivideByZero))
    }
  }

}
