package fr.upem.course1

import fr.upem.course1.Classes.Contact.Email

object Classes {

  trait Gender

  object Gender {

    case object Male extends Gender

    case object Female extends Gender

    case object Other extends Gender

  }

  trait Contact

  object Contact {

    case class Address(number: Int, street: String, city: String) extends Contact

    case class Email(value: String) extends Contact

  }

  final case class Person(name: String, age: Int, gender: Gender, contact: Option[Contact])

  final case class Company(name: String, employees: List[Person])

  val isParisian: Person => Boolean = ???

  val isParisianCompany: Company => Boolean = ???

  val countGender: Gender => Company => Int = ???

  val onlyAdult: Company => Company = ???

  val extractEmail: Company => List[Email] = ???

  val employ: Company => Person => Company = ???

  val fireWithCondition: Company => (Person => Boolean) => Company = ???

  val fusion: String => Company => Company => Company = ???

}
