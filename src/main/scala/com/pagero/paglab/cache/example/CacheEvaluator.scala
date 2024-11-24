package com.pagero.paglab.cache.example

import com.pagero.paglab.cache.example.dao.BookDao

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random

object CacheEvaluator {
  val scaffeineRates = new HitRates
  val ehcacheRates = new HitRates

  def simulateLoad(iterations: Int, totalBooks: Int, bookDao: BookDao): Unit = {
    (1 to iterations).foreach { _ =>
      val randomId = Random.nextInt(totalBooks) + 1

      Await.result(bookDao.findWithEhCache(randomId), Duration.Inf)
      Await.result(bookDao.findWithScaffeineCache(randomId), Duration.Inf)
      // Simulate delay between requests
      //      Thread.sleep(Random.nextInt(800)) // Random delay between 0 and 500ms
    }
    println("scaffeineRates - " + scaffeineRates)
    println("ehcacheRates - " + ehcacheRates)

  }

}
