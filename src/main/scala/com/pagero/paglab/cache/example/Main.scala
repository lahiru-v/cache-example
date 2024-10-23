package com.pagero.paglab.cache.example

import com.pagero.paglab.cache.example.DatabasePackage.db
import com.pagero.paglab.cache.example.dao.BookDao
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {
  println("Hello world...")

  FlywayMigration.migrate()

  private val result = Await.result(db.run(bookQuery.result), Duration.Inf)
  result.foreach(book => println(book))

  private val bookDao = new BookDao()
  private val bookResult = Await.result(db.run(bookDao.findById(1)), Duration.Inf)
  bookResult.foreach(book=> println(book))

  printBook(1)
  printBook(1)
  printBook(2)
  printBook(3)
  printBook(1)
  printBook(4)
  printBook(3)
  println("End of the application....")


  private def printBook(id:Int): Unit = {
    val bookResult = Await.result(db.run(bookDao.findById(id)), Duration.Inf)
    bookResult.foreach(book=> println(book))
  }
}
