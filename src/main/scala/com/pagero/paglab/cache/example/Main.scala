package com.pagero.paglab.cache.example

import com.pagero.paglab.cache.example.DatabasePackage.db
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {
  println("Hello world...")

  FlywayMigration.migrate()

  private val result = Await.result(db.run(bookQuery.result), Duration.Inf)
  result.foreach(book => println(book))

  println("End of the application....")
}
