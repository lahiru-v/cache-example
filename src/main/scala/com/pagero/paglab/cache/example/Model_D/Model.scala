package com.pagero.paglab.cache.example.Model_D
import com.pagero.paglab.cache.example.Main.cache

import scala.collection.mutable
import scala.util.Random
object Model {
  private val alpha = 0.1
  private val gamma = 0.9
  private val epsilon = 0.2

  private val qValues = mutable.Map[(String, Boolean), Double]().withDefaultValue(0.0)


  private def getReward(bookName: String): Double = {
    if (cache.contains(bookName)) 1.0 else -0.5
  }

  private def selectAction(bookName: String): Boolean = {
    if (Random.nextDouble() < epsilon) {
      Random.nextBoolean()
    } else {
      val qUpdate = qValues((bookName, true))
      val qNoUpdate = qValues((bookName, false))
      qUpdate > qNoUpdate
    }
  }

  private def updateQValue(bookName: String, action: Boolean, reward: Double, nextBookName: String): Unit = {
    val nextMaxQ = math.max(qValues((nextBookName, true)), qValues((nextBookName, false)))
    val currentQ = qValues((bookName, action))
    qValues((bookName, action)) = currentQ + alpha * (reward + gamma * nextMaxQ - currentQ)
  }

  def trainAgent(requests: List[Request]): Unit = {
    for (request <- requests) {
      val bookName = request.bookName
      val cacheHit = cache.contains(bookName)

      val shouldCache = selectAction(bookName)

      val reward = getReward(bookName)

      if (shouldCache) {
        cache.add(request.book)
      }

      updateQValue(bookName, shouldCache, reward, bookName)
    }
  }

  def predict(bookName: String): Boolean = {
    selectAction(bookName)
  }
}


//Todo This should work even after the addition of the model to the service