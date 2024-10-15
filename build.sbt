name := "cache-example"

description := "Example service for 2024 PagLabs"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "org.postgresql" % "postgresql" % "42.7.4",
  "org.flywaydb" % "flyway-core" % "9.18.0",
  "com.typesafe" % "config" % "1.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1",
  "com.mchange" % "c3p0" % "0.10.1"
)