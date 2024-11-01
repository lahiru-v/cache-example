package com.pagero.paglab.cache.example

import com.pagero.paglab.cache.example.DatabasePackage.db
import com.pagero.paglab.cache.example.Qlearning.Model
import com.pagero.paglab.cache.example.dao.BookDao
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random

object Main extends App {
  println("Hello world...")

  FlywayMigration.migrate()

  private val result = Await.result(db.run(bookQuery.result), Duration.Inf)
  result.foreach(book => println(book))

  private val bookDao = new BookDao()
  private val bookResult = Await.result(db.run(bookDao.findById(1)), Duration.Inf)
  bookResult.foreach(book=> println(book))

//  fetchAndPrintBook(1)
//  fetchAndPrintBook(1)
//  fetchAndPrintBook(2)
//  fetchAndPrintBook(3)
//  fetchAndPrintBook(1)
//  fetchAndPrintBook(4)
//  fetchAndPrintBook(3)


//  Model.runQLearning()
//  Model.logQTable()
  simulateLoad(3000)
  Model.logQTable()

  println("End of the application....")

  // Function to simulate load
  private def simulateLoad(iterations: Int): Unit = {
    val totalBooks = 6 // Assuming you have 5 books in your dataset

    (1 to iterations).foreach { _ =>
      val randomId = Random.nextInt(totalBooks) + 1
      fetchAndPrintBook(randomId)

      // Simulate delay between requests
      Thread.sleep(Random.nextInt(800)) // Random delay between 0 and 500ms
    }
  }
  private def fetchAndPrintBook(id:Int): Unit = {
    val bookResult = Await.result(db.run(bookDao.findById(id)), Duration.Inf)
//    bookResult.foreach(book=> println(book))
  }
}
