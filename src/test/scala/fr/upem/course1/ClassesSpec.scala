package fr.upem.course1

import fr.upem.course1.Classes.Contact.{Address, Email}
import fr.upem.course1.Classes.{Company, Gender, Person}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class ClassesSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "Company isParisian" should "returns true if city is Paris" in {
    val p = Person("George", 30, Gender.Male, Some(Address(12, "Rue de Rivoli", "Paris")))

    Classes.isParisian(p) should be(true)
  }

  it should "returns false if city is not Paris" in {
    forAll { city: String =>
      val p = Person("George", 30, Gender.Male, Some(Address(12, "Rue de l'église", city)))

      Classes.isParisian(p) should be(false)
    }
  }

  "Company isParisianCompany" should "returns true if all employees are from Paris" in {
    val company = Company("MyCompany",
      Person("George", 30, Gender.Male, Some(Address(10, "Rue de Rivoli", "Paris"))) ::
        Person("Sabrina", 34, Gender.Female, Some(Address(11, "Rue de Rivoli", "Paris"))) ::
        Person("Julie", 41, Gender.Female, Some(Address(12, "Rue de Rivoli", "Paris"))) ::
        Nil
    )

    Classes.isParisianCompany(company) should be(true)
  }

  it should "returns false if all employees are not from Paris" in {
    val company = Company("MyCompany",
      Person("George", 30, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, Some(Address(12, "Rue de Rivoli", "Paris"))) ::
        Person("Julie", 41, Gender.Female, Some(Email("julie@gmail.com"))) ::
        Nil
    )

    Classes.isParisianCompany(company) should be(false)
  }

  "Company countGender" should "count men in a company" in {
    val company = Company("MyCompany",
      Person("George", 30, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, None) ::
        Person("Julie", 41, Gender.Female, None) ::
        Nil
    )

    Classes.countGender(Gender.Male)(company) should be(1)
  }

  it should "count women in a company" in {
    val company = Company("MyCompany",
      Person("George", 30, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, None) ::
        Person("Julie", 41, Gender.Female, None) ::
        Nil
    )

    Classes.countGender(Gender.Female)(company) should be(2)
  }

  "Company onlyAdult" should "filter 18+" in {
    val company = Company("MyCompany",
      Person("George", 17, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, None) ::
        Person("Julie", 16, Gender.Female, None) ::
        Person("Simon", 41, Gender.Male, None) ::
        Nil
    )

    Classes.onlyAdult(company) should be(Company("MyCompany",
      Person("Sabrina", 34, Gender.Female, None) ::
        Person("Simon", 41, Gender.Male, None) ::
        Nil
    ))
  }

  "Company extractEmail" should "extract only email" in {
    val company = Company("MyCompany",
      Person("George", 17, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, Some(Email("sabrina@gmail.com"))) ::
        Person("Julie", 16, Gender.Female, Some(Email("julie@gmail.com"))) ::
        Person("Simon", 41, Gender.Male, Some(Address(10, "Rue de la Mairie", "Bordeaux"))) ::
        Nil
    )

    Classes.extractEmail(company) should be(List(Email("sabrina@gmail.com"), Email("julie@gmail.com")))
  }

  "Company employ" should "add one person to a company" in {
    val company = Company("MyCompany",
      Person("George", 17, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, Some(Email("sabrina@gmail.com"))) ::
        Nil
    )

    val p = Person("Julie", 16, Gender.Female, Some(Email("julie@gmail.com")))

    val newCompany = Classes.employ(company)(p)
    newCompany.name should be(company.name)
    newCompany.employees should contain theSameElementsAs
      (Person("George", 17, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, Some(Email("sabrina@gmail.com"))) ::
        Person("Julie", 16, Gender.Female, Some(Email("julie@gmail.com"))) ::
        Nil)
  }

  "Company fireWithCondition" should "fire only non email employees" in {
    val company = Company("MyCompany",
      Person("George", 17, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, Some(Email("sabrina@gmail.com"))) ::
        Person("Julie", 16, Gender.Female, Some(Email("julie@gmail.com"))) ::
        Person("Simon", 41, Gender.Male, Some(Address(10, "Rue de la Mairie", "Bordeaux"))) ::
        Nil
    )

    Classes.fireWithCondition(company)(_.contact match {
      case Some(_: Email) => true
      case _ => false
    }) should be(
      Company("MyCompany",
        Person("George", 17, Gender.Male, None) ::
          Person("Simon", 41, Gender.Male, Some(Address(10, "Rue de la Mairie", "Bordeaux"))) ::
          Nil
      )
    )
  }

  "Company fusion" should "fusion two companies" in {
    val company1 = Company("MyCompany1",
      Person("George", 17, Gender.Male, None) ::
        Person("Sabrina", 34, Gender.Female, Some(Email("sabrina@gmail.com"))) ::
        Nil
    )

    val company2 = Company("MyCompany2",
      Person("Julie", 16, Gender.Female, Some(Email("julie@gmail.com"))) ::
        Person("Simon", 41, Gender.Male, Some(Address(10, "Rue de la Mairie", "Bordeaux"))) ::
        Nil
    )

    Classes.fusion("MyNewCompany")(company1)(company2) should be(
      Company("MyNewCompany",
        Person("George", 17, Gender.Male, None) ::
          Person("Sabrina", 34, Gender.Female, Some(Email("sabrina@gmail.com"))) ::
          Person("Julie", 16, Gender.Female, Some(Email("julie@gmail.com"))) ::
          Person("Simon", 41, Gender.Male, Some(Address(10, "Rue de la Mairie", "Bordeaux"))) ::
          Nil
      )
    )
  }

}
