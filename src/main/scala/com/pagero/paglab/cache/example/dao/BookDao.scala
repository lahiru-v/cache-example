package com.pagero.paglab.cache.example.dao

import com.pagero.paglab.cache.example.DatabasePackage.db
import com.pagero.paglab.cache.example.cache.ScaffeineCache
import com.pagero.paglab.cache.example.model.Book
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import com.pagero.paglab.cache.example.model.DAL.profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BookDao {

  private val cache = ScaffeineCache.cache

  def findAll: Future[Seq[Book]] = {
    db.run(bookQuery.result)
  }

  def findById(id: Int): Future[Option[Book]] = {
    cache.getIfPresent(id.toString) match {
      case None =>
        println("cache miss - " + id)
        val resultFuture = db.run(bookQuery.filter(_.id === id).result.headOption)
        resultFuture.map { result: Option[Book] =>
          result.foreach { book: Book =>
            cache.put(id.toString, book)
          }
          result
        }
      case Some(book) =>
        println("cache hit - " + id)
        Future.successful(Some(book))
    }
  }
}
