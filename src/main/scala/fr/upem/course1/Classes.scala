package fr.upem.course1

import fr.upem.course1.Classes.Contact.{Address, Email}

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

  val isParisian: Person => Boolean = person =>
    person.contact.exists {
      case Address(_, _, "Paris") => true
      case _ => false
    }

  val isParisianCompany: Company => Boolean = company =>
    company.employees.forall(isParisian)

  val countGender: Gender => Company => Int = gender => company =>
    company.employees.foldRight(0)((person, acc) =>
      if (person.gender == gender)
        acc + 1
      else
        acc
    )

  val onlyAdult: Company => Company = company =>
    company.copy(employees = company.employees.filter(employee => employee.age >= 18))

  val extractEmail: Company => List[Email] = company =>
    company
      .employees
      .collect {
        case Person(_, _, _, Some(email: Email)) => email
      }

  val employ: Company => Person => Company = company => person =>
    company.copy(employees = person +: company.employees)

  val fireWithCondition: Company => (Person => Boolean) => Company = company => condition =>
    company.copy(employees = company.employees.filterNot(condition))

  val fusion: String => Company => Company => Company = newName => company1 => company2 =>
    company1.copy(
      name = newName,
      employees = company1.employees ++ company2.employees
    )

}
