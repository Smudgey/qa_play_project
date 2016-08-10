name := """qa_play_project"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.reactivemongo" %% "reactivemongo" % "0.11.14",
  "org.mindrot" % "jbcrypt" % "0.3m"

)

libraryDependencies += "com.thenewmotion.akka" %% "akka-rabbitmq" % "2.3"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.4.9-RC2"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"

routesGenerator := InjectedRoutesGenerator
