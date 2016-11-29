name := """play-alura"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "6.0.5",
  "com.adrianhurt" % "play-bootstrap_2.11" % "1.1-P25-B3"
)
