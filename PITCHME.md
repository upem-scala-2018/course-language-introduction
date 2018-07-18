# Scala


<img src="https://cdn.worldvectorlogo.com/logos/scala-4.svg" alt="Scala Logo" width="100px" style="border:none;background:none;" class="center"/>

Introduction au langage

---

### En bref

- Sca(lable) La(nguage)
- Créé en 2003 par Martin Odersky, à l'EPFL
- Mixe les paradigmes OO et FP
- Initialement créé pour s'éxécuter sur la JVM

---

### Expérimentez

[https://scastie.scala-lang.org/](https://scastie.scala-lang.org/)

---

### Premiers programmes Scala

---

#### Définition de variable

```scala
val s1 = "Ma variable immutable"
var s2 = "Ma variable mutable"
val s3: String = "Ma variable avec type explicite"
val i = 42
val b = true
val v = {
  val x = 1 + 5
  x
}
```

---

- Pas de ';' en fin d'expression
- Immutabilité
- Inférence de type
- Les variables peuvent être définies sur plusieurs lignes

---

#### Définition de fonction

```scala
val plus1 = (x: Int) => x + 1
val plus2: Int => Int = x => x + 2
val toUpper: String => String = (x: String) => x.toUpperCase
val plus10 = (x: Int) => {
  val plus5 = x + 5
  plus5 + 5
}

plus1(2) // returns 3
toUpper("scala") // returns "SCALA"
toUpper(1) // Compile error
```

---

- "return" implicite: la dernière expression produit la valeur de retour
- Les fonctions sont des variables typées comme les autres: T<sub>domain</sub> => T<sub>codomain</sub>
- Les fonctions peuvent être définies sur plusieurs lignes

---

#### Les types

![Types](https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg)

---

#### Les types courants

```scala
val i: Int = 12
val s: String = "string"
val b: Boolean = true
val tuple: (String, Int) = ("string", 3)
val list: List[String] = List("un", "deux", "trois")
val some: Option[Int] = Some(3)
val none: Option[Int] = None
```

---

#### Les structures de contrôle

```scala
val s: String = if(1 < 10) "true" else "false"
val x = if(1 < 10) "true" // else ()

for(i <- 1 to 10) { println(i) }

condition match {
  case "true" => println("That's true")
  case "false" => println("That's false")
  case _ => println("I don't know")
}
```

---

- Toutes les structures de contrôle résultent en une valeur
- Les **match / case** doivent être exhaustifs !
- **while** existe aussi, mais vous devrez utiliser la récursivité

---

#### Récursivité

```scala
val f: Int => Int = n => n match {
  case x if x <= 0 =>
    println("Zero")
  	0
  case x =>
    println(x)
    f(x-1)
}

f(10)
f(-7)
```

---

### Custom Types et structures

---

#### Class et Trait

```scala
class Vehicule

val v = new Vehicule
```

---

```scala
class Vehicule(val name: String)

val v = new Vehicule("Ma voiture")
println(v.name)
```

---

```scala
class Vehicule(private val name: String)

val v = new Vehicule("Ma voiture")
println(v.name) // fails
```

---

```scala
class Vehicule(val name: String) {
  def getName = name
  def getNameWithprefix(prefix: String) = s"$prefix $name"
  override def toString = name
}

val v = new Vehicule("Ma voiture")
println(v.name)
println(v)
println(v.getName)
println(v.getNameWithprefix("Nom de ma voiture :"))
```

---

```scala
trait Wheels {
  val wheelsCount: Int
}
trait TwoWheels extends Wheels {
  override val wheelsCount = 2
}
trait FourWheels extends Wheels {
  override val wheelsCount = 4
}
class Vehicule(val name: String)

val v = new Vehicule("Ma voiture") with FourWheels
println(v.name)
println(v.wheelsCount)
```

---

```scala
trait Color
trait Red extends Color
trait Green extends Color
trait Wheels {
  val wheelsCount: Int
}
trait TwoWheels extends Wheels {
  override val wheelsCount = 2
}
trait FourWheels extends Wheels {
  override val wheelsCount = 4
}
class Vehicule(val name: String)

val green = new Vehicule("Ma voiture") with FourWheels with Green
val red = new Vehicule("Ma voiture") with FourWheels with Red

def onlyGreen(v: Vehicule with Green) = v

onlyGreen(green) // compiles
onlyGreen(red) // fails
```

---

- Les (abstract) classes sont semblables à celles de Java, et assurent l'intéropérabilité
- Le constructeur principal se trouve dans la déclaration
- Les traits sont l'équivalent des interfaces en Java > 8
- Les traits permettent l'héritage multiple (et la résolution du problème du diamand)
- Les traits peuvent être utilisés pour ajouter du comportement à une classe
- Les traits peuvent être utilisés pour préciser (refined) un typage

---

#### Case class et Object

```scala
case class Person(name: String, age: Int)

val p1 = Person("Mathieu", 30)
val p2 = Person("Matthieu", 30)
println(p1.name)
println(p1.age)
println(p1.toString)
println(p1.hashCode)
println(p1 == p2) // En Scala '==' invoque la méthode 'equals' ;)
```

---

- Les case class représentent des Value Object
- Les case class génèrent les méthodes toString, equals et hashCode
- Les champs des case class sont publics et immuables par défaut
- Pas besoin de 'new' pour instancier une case class

---
