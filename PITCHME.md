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

### En bref

- Langage compilé, mais distribué avec un interpéteur CLI
- Typage fort et statique
- Forte communauté (nombreux projets open-source, conférences, meetups, discussions)
- Ecosystème riche (SBT, Play, Akka, Shapeless, Cats, Scalaz, Dotty, ...)
- Largement utilisé en entreprise : web, big data, streaming

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
val s4 = s"Valeur de i : $i"
val s5 =
  """
    |String sur
    |plusieurs lignes
  """.stripMargin
val b = true
val v = {
  val x = 1 + 5
  x
}
```

---

- Pas de **;** en fin d'expression
- Immutabilité
- Inférence de types
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

- **return** implicite: la dernière expression du bloc produit la valeur de retour
- Les fonctions sont des variables typées T<sub>domain</sub> => T<sub>codomain</sub>
- Les fonctions peuvent être définies sur plusieurs lignes

---

#### Les types

![Types](https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg)

- Il n'y a pas de type primitif, tout est objet (Int, Boolean, Float, ...)
- Il n'y a pas d'opérateur (ex : **+**, **-**, **!** sont des méthodes)

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

aNumber match {
  case 1 => println("That's one")
  case 2 => println("That's two")
  case _ => println("I don't know")
}
```

---

- Toutes les structures de contrôle résultent en une valeur
- Les **match / case** doivent être exhaustifs !
- **while** existe aussi, mais préférez plutôt la récursivité

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
class Vehicule(val name: String = "Ma voiture")

val v = new Vehicule
println(v.name)
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
- Les traits sont l'équivalent des interfaces en Java >= 8
- Les traits permettent l'héritage multiple (et la résolution du problème du diamand)
- Les traits peuvent être utilisés pour ajouter du comportement à une classe
- Les traits peuvent être utilisés pour préciser (refined) un typage

---

#### Pattern Matching

- Permet de destructurer une variable, et d'asserter sur ses propriétés

```scala
"UPEMLV" match {
  case x if x.length < 3 => "Too short"
  case x if x.length > 10 => "Too long"
  case _ => "Correct"
}
```

---

- Le Pattern Matching doit toujours être exhaustif
- Le premier cas qui match est choisi !

```scala
Option(4) match {
  case Some(7) => Some(77)
  case Some(x) if x > 5 => Some(x + 10)
  case Some(x) if x < 5 => Some(x + 20)
  case Some(5) => Some(55)
  case None => Some(0)
}
```

---

- Attention !

```scala
Option(4) match {
  case Some(x) => "Matches all Some"
  case Some(6) => "This case is ignored !!!!"
  case None => "Nothing"
}
```

---

- Est une expression => retourne une valeur

```scala
val x = List(1, 2, 3) match {
  case 1 :: 3 :: xs => "2 firsts are primes"
  case 1 :: Nil => "1 element list"
  case 1 :: 2 :: 3 :: 4 :: xs => "at least 4 elements list"
  case _ => "Other structure"
}
```

---

- Le pattern matching est réalisable sur toutes les structures avec une méthode *unapply*

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
println(p1 == p2) // En Scala "==" invoque la méthode "equals" ;)

def isAdult(p: Person) = p match {
  case Person(_, age) if age >= 18 => true
  case _ => false
}

isAdult(p1) // returns true
```

---

- Les case class représentent des Value Objects
- Les case class génèrent les méthodes toString, equals et hashCode
- Les champs des case class sont publics et immuables par défaut
- Pas besoin de **new** pour instancier une case class (on verra pourquoi)
- On peut utiliser **match / case** sur les case class (on verra pourquoi)

---

```scala
object Constants {
  val protocol = "http"
  val host = "locahost"
  val port = 9000
  
  val address = s"$protocol://$host:$port"
}

println(Constants.host)
println(Constants.port)
println(Constants.address)

```

- Un object est un singleton (instance unique), son type est *nom*.type
- Les object peuvent être utilisés pour stocker des constantes

---

```scala
trait Color
object Green extends Color
object Orange extends Color
object Red extends Color
```

- Les object peuvent être utilisés pour décrire un Sum Type (Enum)

---

#### Companion object

```scala
class Person(val name: String) {
  import Person._
  def upperName = toUpper(name)
}
object Person {
  private def toUpper(s: String) = s.toUpperCase
}

val p = new Person("Mathieu")
println(p.upperName)
```

- Un object du même nom qu'une classe (et défini dans le même fichier) est appelé **Objet Compagnon**
- Une classe peut accéder aux méthodes privées de son compagnon
- Les champs statiques (en Java) sont des constantes du companion object en Scala
- (Les import sont possible dans tous les scopes (fonction, méthode, classe...))

---

```scala
class Person(val name: String)

object Person {
  def apply(name: String): Person = new Person(name)
  def unapply(p: Person): Option[String] = Some(p.name)
}

val p = Person("Mathieu")
p match {
  case Person("Mathieu") => println("C'est Mat !")
  case _ => println("Je ne sais pas qui c'est")
}
```

- la méthode **unapply** s'appelle l'extractor => extrait les champs (déconstruit)
- la méthode **apply** s'appelle le constructeur => construit la classe

---

```scala
object Split {
  def unapply(s: String) = s match {
    case x if x.length % 2 == 0 =>
    	val l = x.length / 2
    	Some(x.substring(0, l), x.substring(l, x.length))
    case _ => None
  }
}

"abcdef" match {
  case Split(a, b) => println(s"$a $b")
  case _ => println("Can not split")
}
```

- **unapply** est utilisée pour la destructuration dans les Pattern Matching
- Déjà implémentée pour de nombreuses structures
- Les methodes **apply** et **unapply** sont générées automatiquement pour les case class

---

#### Exemples

```scala
val some = Option("Mathieu")

some match {
  case None => println("Nothing in it")
  case Some(x) => println(s"The value is $x")
}
```

---

```scala
def firstTwo[A](l: List[A]) = l match {
  case x :: y :: xs => println(s"$x $y")
  case _ => println("Must contain at least 2 elements")
}

firstTwo(List(1, 3, 5))
firstTwo(List(1))
firstTwo(List("un", "cinq", "six"))
firstTwo(List(true, false, true))
```

---

### ByName / ByValue parameters

```scala
def doNothing(o: => Unit) = ()

doNothing(println("Is it printed ?"))
```

---

### Laziness

```scala
lazy val x = {
  println("Evaluated")
  4
} 
```

- La variable 'x' sera évaluée lors de la première référence
- Contrairement à un 'def', 'val' n'est évaluée qu'une seule fois !

---

### Généricité

```scala
def identity[A](a: A) = a
```

- *A* est un type générique
- Lire "pour tout type *A*"

---

```scala
case class Vehicule(brand: String)
case class Person[A](name: String, stuff: A)
case class Tuple[A, B](first: A, second: B)
case class Wrapper[F[_]](wrapper: F[String])
```

- Vehicule est un **type**, son kind est "*" 
- Person est un **type constructor**, il lui faut un type pour devenir lui-même un type.
- Person\[A] est un type, son kind est "*"
- Tuple\[A, B] est un type (\*), le kind de Tuple\[A, \_] est \* -> \*, le kind de Tuple est \* -> \* -> \*
- Wrapper est un type constructor qui prend un type constructor en argument, le kind de Wrapper\[F\[\_]] est (\* -> \*) -> \*. On parle aussi de higher-kinded type.

---

```scala
case class Person[A](name: String, stuff: A)

type PersonWithBoolean = Person[Boolean]
```
- Scala permet de faire des **Type Alias**

---

```scala
class Test
class TestWith[A]

def display[F[_], A](fa: F[A]) = println(fa)
display(List(1, 2, 3)) // compiles
display(new TestWith[String]) // compiles
display(new Test) // fails
```

---

### Les fonctions

```scala
val identity: A => A = (a: A) => a
```

- Le type de cette fonction est **Function1\[A, A]**, aussi écrit **=>\[A, A]**, ou encore **A => A**
- Plus généralement, un type **T\[A, B]** peut s'écrire **A T B**
- Les fonctions sont typées de manière générique, FunctionX, X étant le nombre de paramètres

---

#### Higher Order Functions

```scala
val applyF: (String => Int) => String => Int = f => s => f(s)
val length = (s: String) => s.length

applyF(length)("Salut")
```

- Une fonction est une valeur comme une autre
- Elle peut donc être passée en paramètre, ou être retournée

---

#### Higher Order Functions : version générifiée

```scala
def applyF[A, B]: (A => B) => A => B = f => s => f(s)
val length = (s: String) => s.length
val plus10 = (i: Int) => i + 10

applyF(length)("Salut")
applyF(plus10)(10)
```

---

#### Curryfication

```scala
val currify: ((String, String) => String) => String => String => String = (f: (String, String) => String) =>
  (first: String) =>
    (second: String) =>
      f(first, second)


val concat = (x: String, y: String) => s"$x $y"

val f = currify(concat)

f("Mathieu")("Dulac")
```

- La curryfication permet de transformer une fonction à plusieurs paramètres en fonction à 1 paramètre qui retourne une fonction, ...

---

#### Curryfication : version générifiée

```scala
def currify[A, B, C]: ((A, B) => C) => A => B => C = (f: (A, B) => C) =>
  (first: A) =>
    (second: B) =>
      f(first, second)

val concat: (String, String) => String = (s1, s2) => s"$s1 $s2"
val sum: (Int, Int) => Int = (i1, i2) => i1 + i2

currify(concat)
currify(sum)
```

---

#### Implicits

- Permet "d'injecter" un paramètre à la compilation dans une fonction ou dans une méthode

```scala
implicit val s: String = "Implicit string value"
def print(implicit x: String) = println(x)

print // prints "Implicit string value"
```

---

- Souvent utilisé pour injecter un contexte : ExecutionContext, DB Driver, Session, ... 
```scala
override def map[S](f: Nothing => S)(implicit executor: ExecutionContext): Future[S] = this
```
---

#### View bound

- Permet de voir un type "en tant que"

```scala
implicit val convert: Int => String = (i: Int) => i.toString
def printString[A](s: A)(implicit c: A => String) = println(c(s))

printString(3) // compiles
printString(true) // No implicit view available from Boolean => String
```

---

#### Evidence

- Permet de prouver une propriété à compile-time
- Introduction aux typeclass du cours 3

```scala
trait Exists[A]
implicit val IntExists = new Exists[Int]{}

def exists[A](a: A)(implicit ev: Exists[A]): A = a

exists(5) // compiles
exists("string") // could not find implicit value for parameter ev: Playground.this.Exists[String]
```

---

#### Implicit conversion

- Pas vraiment de bonne raison de l'utiliser, à ma connaissance

```scala
import scala.language.implicitConversions
implicit def convertToString(i: Int): String = i.toString

def concat(x: String, y: String) = x ++ y

concat(4, 2) // returns 42
```

---

#### Extensions methods

- Permet d'étendre des types existants

```scala
object Converters {
  implicit class StringPlus(val s: String) extends AnyVal {
  	def capitalizeEachWord = s.split(' ').map(_.capitalize).mkString(" ")
	}
}

object Main extends App {
  import Converters._
 	println("hello upemlv, it's good to see you".capitalizeEachWord)
}
```

---

#### L'expressivité par les types

- Exprimer l'absence probable de valeur : Option\[A]
- Exprimer la potentielle absence ou multitude de valeurs : List\[A]
- Exprimer la potentielle multitude de valeurs : NonEmptyList\[A] (pas dans le SDK)
- Exprimer un traitement pouvant échouer : Try\[A]
- Exprimer un traitement asynchrone : Future\[A]
- Exprimer un traitement lazy : Task\[A] (pas dans le SDK)
- Exprimer un Union Type : Either\[A, B]
...

---