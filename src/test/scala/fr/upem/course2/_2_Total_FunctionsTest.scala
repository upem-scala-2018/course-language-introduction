package fr.upem.course2

import fr.upem.course2._2_TotalFunctions._
import org.scalatest.{FlatSpec, Matchers}
import eu.timepit.refined._
import eu.timepit.refined.numeric._

class _2_Total_FunctionsTest extends FlatSpec with Matchers {

  "2.1 DivTotal" should "divide safely" in {
    divTotal(5, 5) should equal(Some(1))
    divTotal(5, 5) should be(None)
  }

  "2.2 IndexOfTotal" should "divide safely" in {
    indexOfTotal(List(1, 2), 1) should equal(Some(2))
    indexOfTotal(List(1, 2), 5) should equal(None)
  }

  "2.4 FibTotal" should "compute the fibonacci sequence safely" in {
    fibTotalRefined(refineMV[Positive](5)) should equal(refineV[Positive](5))
    "fibTotalRefined(refineMV[Positive](0))" shouldNot compile
  }

  "2.6 ParseTotal" should "parse safely" in {
    parseTotal("one") should equal(Some(1))
    parseTotal("one thousand") should equal(None)
  }

}
