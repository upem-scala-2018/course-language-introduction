package fr.upem.course1

import fr.upem.course1.Implicits.{Id, _}
import org.scalatest.enablers.Containing
import org.scalatest.{FlatSpec, Matchers}

class ImplicitSpec extends FlatSpec with Matchers {

  "Implicit .some" should "add '.some' to any string to turn it into an option" in {
    "I must be an option".some should be(Some("I must be an option"))
  }

  it should "add '.some' to any int to turn it into an option" in {
    3.some should be(Some(3))
  }

  it should "add '.some' to any boolean to turn it into an option" in {
    true.some should be(Some(true))
  }

  it should "add '.some' to any boolean to turn it into an option, and assert as a containing" in {
    true.some should contain(true)
  }

  "Implicit .isTrue" should "add '.isTrue' to any string to test it" in {
    "true".isTrue should be(true)
  }

  it should "add '.isTrue' to any string to test it" in {
    "other".isTrue should be(false)
  }

  "Implicit .id" should "transform a string into an id" in {
    "123".id should be(Id("123"))
  }

  it should "transform a string into an id and assert as a containing" in {
    implicit val containing: Containing[Id] = new Containing[Id] {
      override def contains(container: Id, element: Any): Boolean = container.value == element

      override def containsOneOf(container: Id, elements: Seq[Any]): Boolean = elements.contains(container.value)

      override def containsNoneOf(container: Id, elements: Seq[Any]): Boolean = !elements.contains(container.value)
    }
    "123".id should contain("123")
  }

}
