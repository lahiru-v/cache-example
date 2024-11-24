package com.pagero.paglab.cache.example.dao

import com.pagero.paglab.cache.example.model.Book
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import com.pagero.paglab.cache.example.model.DAL.profile.api._
import com.pagero.paglab.cache.example.tinylfu.LfuCache.cache
import slick.dbio.{DBIO, DBIOAction, Effect, NoStream}

import scala.collection.JavaConverters.asScalaIteratorConverter
import scala.concurrent.ExecutionContext.Implicits.global

class BookDao {
  def findById(id: Int): DBIOAction[Option[Book], NoStream, Effect.Read] = {
    cache.get(id.toString) match {
      case null =>
        println("cache miss - " + id)
        val dbAction = bookQuery.filter(_.id === id).result.headOption
        dbAction.map { result =>
          result.foreach { book =>
            cache.put(id.toString, book) // Store in Ehcache
          }
          result
        }
      case book =>
        println("cache hit - " + id)
        DBIO.successful(Some(book)) // Return cached value
    }

  }
}
