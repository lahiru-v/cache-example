package com.pagero.paglab.cache.example.tinylfu

import com.pagero.paglab.cache.example.model.Book
import org.ehcache.CacheManager
import org.ehcache.config.builders.{CacheConfigurationBuilder, CacheManagerBuilder, ResourcePoolsBuilder}
import org.ehcache.event.{EventFiring, EventOrdering, EventType}

object LfuCache {
  val cacheSize = 10
  // Create a CacheManager
  val cacheManager: CacheManager = CacheManagerBuilder.newCacheManagerBuilder.build(true)

  // Define cache configuration
  private val cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(
    classOf[String], classOf[Book],
    ResourcePoolsBuilder.heap(cacheSize) // Limit the cache to 10 entries
  )

  // Create a TinyLFU policy
  private val tinyLFUPolicy = new TinyLFUEvictionPolicy[String](maxSize = cacheSize)

  // Build the cache
  val cache = cacheManager.createCache("bookCache", cacheConfig)

  // Register the TinyLFU eviction listener
  cache.getRuntimeConfiguration.registerCacheEventListener(
    new TinyLFUEventListener(tinyLFUPolicy, cache),
    EventOrdering.ORDERED,
    EventFiring.SYNCHRONOUS,
    EventType.CREATED, EventType.UPDATED
  )
}
