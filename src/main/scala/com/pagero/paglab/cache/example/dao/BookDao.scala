package com.pagero.paglab.cache.example.dao

import com.pagero.paglab.cache.example.CacheEvaluator.{ehcacheRates, scaffeineRates}
import com.pagero.paglab.cache.example.DatabasePackage.db
import com.pagero.paglab.cache.example.cache.{EhCache, ScaffeineCache}
import com.pagero.paglab.cache.example.model.Book
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import com.pagero.paglab.cache.example.model.DAL.profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BookDao {

  private val scaffeineCache = ScaffeineCache.cache
  private val ehCache = EhCache.cache

  def findAll: Future[Seq[Book]] = {
    db.run(bookQuery.result)
  }

  def findWithScaffeineCache(id: Int): Future[Option[Book]] = {
    scaffeineCache.getIfPresent(id.toString) match {
      case None =>
        println("cache miss - " + id)
        scaffeineRates.missed()
        val resultFuture = db.run(bookQuery.filter(_.id === id).result.headOption)
        resultFuture.map { result: Option[Book] =>
          result.foreach { book: Book =>
            scaffeineCache.put(id.toString, book)
          }
          result
        }
      case Some(book) =>
        println("cache hit - " + id)
        scaffeineRates.hit()
        Future.successful(Some(book))
    }
  }

  def findWithEhCache(id: Int): Future[Option[Book]] = {
    Option(ehCache.get(id.toString)) match {
      case Some(book) =>
        println("cache hit - " + id)
        ehcacheRates.hit()
        Future.successful(Some(book))

      case _ =>
        println("cache miss - " + id)
        ehcacheRates.missed()
        val resultFuture = db.run(bookQuery.filter(_.id === id).result.headOption)
        resultFuture.map { result: Option[Book] =>
          result.foreach { book: Book =>
            ehCache.put(id.toString, book)
          }
          result
        }
    }
  }
}
