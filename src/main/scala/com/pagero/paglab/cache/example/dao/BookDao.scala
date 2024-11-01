package com.pagero.paglab.cache.example.dao

import com.pagero.paglab.cache.example.Qlearning.Model.{cache, cacheSize, getReward, getState, selectAction, updateFrequencyAndRecency, updateQTable}
import com.pagero.paglab.cache.example.model.Book
import com.pagero.paglab.cache.example.model.DAL.bookQuery
import slick.dbio.{DBIO, DBIOAction, Effect, NoStream}
import com.pagero.paglab.cache.example.model.DAL.profile.api._

import scala.concurrent.ExecutionContext.Implicits.global

class BookDao {
  def findById(id: Int): DBIOAction[Option[Book], NoStream, Effect.Read] = {
    val state = getState(id) // Get state with frequency and recency
    val action = selectAction(state) // Choose action based on Q-Table
    println(s"id - $id - $state - $action")


    // Simulate the request and get the next state
    val reward = getReward(id.toString)

    updateFrequencyAndRecency(id) // Update frequency and recency maps
    val nextState = getState(id)
    // Update Q-Table with the experience
    updateQTable(state, action, reward,nextState)

    if (action == "Cache") {
      cache.get(id.toString) match {
        case null =>
          println("cache miss - " + id)
          val dbAction = bookQuery.filter(_.id === id).result.headOption
          dbAction.map { result =>
            result.foreach { book =>
              cache.put(id.toString, book) // Store in Ehcache
              cacheSize = cacheSize +1
            }
            result
          }
        case book =>
          println("cache hit - " + id)
          DBIO.successful(Some(book)) // Return cached value
      }
    } else {
      println("cache evicted - " + id)
      if(cacheSize > 4){
        cache.remove(id.toString) // Evict item from cache
        cacheSize = cacheSize - 1
      }

      bookQuery.filter(_.id === id).result.headOption
    }
  }
}
