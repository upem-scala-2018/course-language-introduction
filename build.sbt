lazy val `upem-course-1` = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "fr/upem",
      scalaVersion := "2.12.6",
      version := "1.0.0"
    )),
    name := "language-introduction",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "eu.timepit" %% "refined" % "0.9.2"
  )
