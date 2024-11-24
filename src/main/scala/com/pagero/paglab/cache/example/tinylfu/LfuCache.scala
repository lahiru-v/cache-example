package com.pagero.paglab.cache.example.tinylfu

import com.pagero.paglab.cache.example.model.Book
import org.ehcache.CacheManager
import org.ehcache.config.builders.{CacheConfigurationBuilder, CacheManagerBuilder, ResourcePoolsBuilder}
import org.ehcache.event.{EventFiring, EventOrdering, EventType}

object LfuCache {
  val cacheSize = 10
  val cacheManager: CacheManager = CacheManagerBuilder.newCacheManagerBuilder.build(true)

  private val cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(
    classOf[String], classOf[Book],
    ResourcePoolsBuilder.heap(cacheSize)
  )

  private val tinyLFUPolicy = new TinyLFUEvictionPolicy[String](maxSize = cacheSize)

  val cache = cacheManager.createCache("bookCache", cacheConfig)

  cache.getRuntimeConfiguration.registerCacheEventListener(
    new TinyLFUEventListener(tinyLFUPolicy, cache),
    EventOrdering.ORDERED,
    EventFiring.SYNCHRONOUS,
    EventType.CREATED, EventType.UPDATED
  )
}
