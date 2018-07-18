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
- Les fonctions sont des variables typées comme les autres: T ,,d,, => T ,,cd,,
- Les fonctions peuvent être définies sur plusieurs lignes

---

#### Les types

![Types](https://docs.scala-lang.org/resources/images/tour/unified-types-diagram.svg)
