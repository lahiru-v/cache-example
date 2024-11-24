//package com.pagero.paglab.cache.example.Qlearning
//
//import com.pagero.paglab.cache.example.model.Book
//import org.ehcache.{Cache, CacheManager}
////import org.ehcache.config.CacheConfiguration
//import org.ehcache.config.builders.{CacheConfigurationBuilder, CacheManagerBuilder, ResourcePoolsBuilder}
//import org.ehcache.config.units.EntryUnit
//
//import scala.collection.mutable
//import java.time.LocalDateTime
//import scala.util.Random
//
//object Model {
//  val startTime = LocalDateTime.now
//  // Initialize Ehcache cache manager and cache
//  val cacheManager: CacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true)
//
//  val cache: Cache[String, Book] = cacheManager.createCache("smartCache",
//    CacheConfigurationBuilder.newCacheConfigurationBuilder(classOf[String], classOf[Book],
//      ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, EntryUnit.ENTRIES)))
//
//  // Define States and Actions
//  val actions = Seq("Cache", "Evict") // Two actions: Cache or Evict
//  var cacheSize = 0
//  // Q-Table to store the Q-values for each state-action pair
//  val qTable: mutable.Map[(Int, String), Double] = mutable.Map().withDefaultValue(0.0)
//  //  qTable(("Recent", "Cache")) = 0
//  //  qTable(("Recent", "Evict")) = 0
//  //  qTable(("MediumRecency", "Cache")) = 0
//  //  qTable(("MediumRecency", "Evict")) = 0
//  //  qTable(("Stale", "Cache")) = 0
//  //  qTable(("Stale", "Evict")) = 0
//
//  // Q-Learning parameters
//  val learningRate = 0.2
//  val discountFactor = 0.95
//  val explorationRate = 0.2
//
//  // Tracking frequency and recency
//  val frequencyMap: mutable.Map[Int, Int] = mutable.Map().withDefaultValue(0)
//  val recencyMap: mutable.Map[Int, LocalDateTime] = mutable.Map().withDefaultValue(LocalDateTime.MIN)
//
//  // Update frequency and recency when data is accessed
//  def updateFrequencyAndRecency(dataId: Int): Unit = {
//    val currentTIme = LocalDateTime.now
//
//    frequencyMap(dataId) += 1
//    startTime
//    recencyMap(dataId) = currentTIme
//  }
//
//  // Define frequency categories
//  def getFrequencyCategory(count: Int): String = {
//    val totalTimeSeconds = java.time.Duration.between(startTime, LocalDateTime.now).toSeconds +1
//    val frequency = count / totalTimeSeconds
//    if (frequency <= 0.1) "LowFrequency"
//    else if (frequency <= 0.5) "MediumFrequency"
//    else "HighFrequency"
//  }
//
//  // Define recency categories
//  private def getRecencyCategory(lastAccessTime: LocalDateTime): String = {
//    val minutesSinceLastAccess = java.time.Duration.between(lastAccessTime, LocalDateTime.now()).toSeconds
//    if (minutesSinceLastAccess <= 1) "Recent"
//    else if (minutesSinceLastAccess <= 2) "MediumRecency"
//    else "Stale"
//  }
//
//  def getState(dataId: Int): String = {
//    val frequencyCategory = getFrequencyCategory(frequencyMap(dataId))
//    val recencyCategory = getRecencyCategory(recencyMap(dataId))
//    s"$frequencyCategory-$recencyCategory" // Example: "LowFrequency-Recent"
//  }
//
//  // Reward System
//  def getReward(dataId: String): Double = {
//    val element = cache.get(dataId)
//    if (element != null) {
//      1.0
//    } else {
//      -1.0
//    }
//    //    case "CacheHit" => 1.0 // Positive reward for cache hit
//    //    case "CacheMiss" => -1.0 // Negative reward for cache miss
//  }
//
//  def simulateRequest(dataId: Int): String = {
//    val element = cache.get(dataId.toString)
//    if (element != null) {
//      updateFrequencyAndRecency(dataId)
//      "CacheHit"
//    } else {
//      "CacheMiss"
//    }
//  }
//
//  // Update Q-Table based on the new state, including frequency and recency
//  def updateQTable(state: String, action: String, reward: Double, nextState: String): Unit = {
//    val oldQValue = qTable((state, action))
//    val maxNextQ = actions.map(a => qTable((nextState, a))).max
//    val newQValue = oldQValue + learningRate * (reward + discountFactor * maxNextQ - oldQValue)
//    qTable((state, action)) = newQValue
//  }
//
//  // Selecting an action using the enhanced state
//  def selectAction(state: String): String = {
//    if (Random.nextDouble() < explorationRate) {
//      actions(Random.nextInt(actions.size))
//    } else {
//      actions.maxBy(a => qTable((state, a)))
//    }
//  }
//
//  def runQLearning(): Unit = {
//    for (episode <- 1 to 1000) { // Run for a number of episodes
//      val id = Random.nextInt(20) // Simulate random data requests
//      val state = getState(id) // Get enhanced state with frequency and recency
//
//      // Take an action based on the current state
//      val action = selectAction(state)
//
//      // Simulate the request and get the next state
//      val reward = getReward(simulateRequest(id))
//      val nextState = getState(id)
//
//      // Update Q-Table with the experience
//      updateQTable(state, action, reward, nextState)
//    }
//  }
//
//
//  // Function to log Q-table in a tabular format
//  def logQTable(): Unit = {
//    println(qTable)
//    // Extract all states and actions from the Q-table
//    val states = qTable.keys.map(_._1).toSet
//    val actions = qTable.keys.map(_._2).toSet
//    // Print header (actions as columns)
//    println(f"${"State/Action"}%-35s" + actions.map(a => f"$a%-10s").mkString(" "))
//
//    // Print each state and its corresponding Q-values for each action
//    for (state <- states) {
//      val row = actions.map { action =>
//        f"${qTable((state, action))}%-10.2f" // Format Q-values to 2 decimal places
//      }.mkString(" ")
//
//      println(f"$state%-35s" + row)
//    }
//  }
//
//}
