package fr.upem.course1

import java.nio.file.Paths

import fr.upem.course1.Program.Country
import org.scalatest.{FlatSpec, Matchers}

class ProgramSpec extends FlatSpec with Matchers {

  "Program" should "read correctly JSON file" in {
    // Given
    val path = Paths.get(getClass.getResource("countries.json").toURI)

    // When
    val content = Program.pathAsString(path)

    // Then
    content.get shouldNot be('empty)
  }

  it should "turn string into countries" in {
    // Given
    val content =
      """
        |[
        |{"name": {"common": "France"}, "independent": true},
        |{"name": {"common": "Belgium"}, "independent": true}
        |]
      """.stripMargin

    // When
    val countries = Program.stringToCountries(content)

    // Then
    countries.get should be(List(Country("France", independent = true), Country("Belgium", independent = true)))
  }

  it should "turn string into countries" in {
    // Given
    val countries = Country("France", independent = true) ::
      Country("Antarctica", independent = false) ::
      Country("Belgium", independent = true) ::
      Nil

    // When
    val independents = Program.independentCountries(countries)

    // Then
    independents should be(List(Country("France", independent = true), Country("Belgium", independent = true)))
  }

}
