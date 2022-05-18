import sbtassembly.AssemblyKeys.assembly

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "DataEngineer_ScalaPractice",

    assembly / mainClass := Some("Main"),
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs@_*) => MergeStrategy.discard
      case x => MergeStrategy.first
    },

    libraryDependencies ++= Seq(
      "com.ibm.db2" % "jcc" % "11.5.7.0",
      "mysql" % "mysql-connector-java" % "8.0.27",
      "org.scala-lang" % "scala-library" % "2.13.8"
    )
  )
