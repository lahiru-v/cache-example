package com.pagero.paglab.cache.example.dao

import com.pagero.paglab.cache.example.Qlearning.Model.{cache, getState, selectAction, updateFrequencyAndRecency}
import com.pagero.paglab.cache.example.model.Book
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import slick.dbio.{DBIO, DBIOAction, Effect, NoStream}
import com.pagero.paglab.cache.example.model.DAL.profile.api._
import scala.concurrent.ExecutionContext.Implicits.global

class BookDao {
   def findById(id: Int): DBIOAction[Option[Book], NoStream, Effect.Read] = {
    val state = getState(id) // Get state with frequency and recency
    val action = selectAction(state) // Choose action based on Q-Table

    if (action == "Cache") {
      cache.get(id.toString) match {
        case null =>
          println("cache miss - " + id)
          val dbAction = bookQuery.filter(_.id === id).result.headOption
          dbAction.map { result =>
            result.foreach { body =>
              updateFrequencyAndRecency(id) // Update frequency and recency maps
              cache.put(id.toString, body) // Store in Ehcache
            }
            result
          }
        case book =>
          println("cache hit - " + id)

          DBIO.successful(Some(book)) // Return cached value
      }
    } else {
      println("cache evicted - " + id)
      cache.remove(id.toString) // Evict item from cache
      bookQuery.filter(_.id === id).result.headOption
    }
  }
}
