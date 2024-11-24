package com.pagero.paglab.cache.example

import com.pagero.paglab.cache.example.CacheEvaluator.simulateLoad
import com.pagero.paglab.cache.example.dao.BookDao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {
  println("Hello world...")

  FlywayMigration.migrate()
  private val bookDao = new BookDao()

  private val result = Await.result(bookDao.findAll, Duration.Inf)
  println("no of books: " + result.size)
  //
  //  private val bookResult = Await.result(bookDao.findWithScaffeineCache(1), Duration.Inf)
  //  bookResult.foreach(book => println(book))


  simulateLoad(100000, 200, bookDao)

  println("End of the application....")

}
