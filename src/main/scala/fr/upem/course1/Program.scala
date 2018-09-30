package fr.upem.course1

import java.nio.file.Path

import scala.util.Try

object Program extends App {

  final case class Country(commonName: String, independent: Boolean)

  def pathAsString(p: Path): Try[String] = ???

  def stringToCountries(value: String): Try[List[Country]] = ???

  def independentCountries(countries: List[Country]): List[Country] = ???

  override def main(args: Array[String]) = {
    // Implements a program using the methods above, to filter only the independent countries
    // Feel free to use a JSON parsing library (tips : "Play Json" is nice)
    // If you finish this one, I've got couples of ideas before leaving the room ;)
  }

}
