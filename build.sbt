name := "cache-example"

description := "Example service for 2024 PagLabs"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
//  "org.scalikejdbc" %% "scalikejdbc" % "3.5.0", // Optional, if you need for connection
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.postgresql" % "postgresql" % "42.7.4",
  "org.flywaydb" % "flyway-database-postgresql" % "10.16.0",
  "com.typesafe" % "config" % "1.4.1",
)