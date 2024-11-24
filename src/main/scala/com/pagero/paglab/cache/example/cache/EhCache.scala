package com.pagero.paglab.cache.example.cache

import com.pagero.paglab.cache.example.model.Book
import org.ehcache.Cache
import org.ehcache.config.builders.{CacheConfigurationBuilder, CacheManagerBuilder, ExpiryPolicyBuilder, ResourcePoolsBuilder}

import java.time.Duration

object EhCache {
  private val cacheManagerBuilder = CacheManagerBuilder.newCacheManagerBuilder()

  private val expiryPolicy = ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(1))

  private val cacheConfigBuilder = CacheConfigurationBuilder.newCacheConfigurationBuilder(
    classOf[String],
    classOf[Book],
    ResourcePoolsBuilder.heap(50))
  cacheConfigBuilder.withExpiry(expiryPolicy)

  private val cacheManager = cacheManagerBuilder.withCache("book-cache", cacheConfigBuilder.build()).build()
  cacheManager.init()

  val cache: Cache[String, Book] = cacheManager.getCache("book-cache", classOf[String], classOf[Book])
}
