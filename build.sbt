ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "DataEngineer_ScalaPractice"
  )

libraryDependencies ++= Seq(
  "com.ibm.db2" % "jcc" % "11.5.7.0"
)
