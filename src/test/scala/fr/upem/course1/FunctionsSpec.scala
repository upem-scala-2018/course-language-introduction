package fr.upem.course1

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class FunctionsSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "Function sum" should "sum two integers" in {
    forAll { (m: Int, n: Int) =>
      Functions.sum(m, n) should be(m + n)
    }
  }

  "Function sumAll" should "sum integers of given list" in {
    forAll { xs: List[Int] =>
      Functions.sumAll(xs) should be(xs.sum)
    }
  }

  "Function concat" should "concat two strings" in {
    forAll { (m: String, n: String) =>
      Functions.concat(m, n) should be(s"$m$n")
    }
  }

}
