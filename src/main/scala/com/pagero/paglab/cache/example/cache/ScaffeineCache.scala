package com.pagero.paglab.cache.example.cache

import com.github.blemale.scaffeine.{Cache, Scaffeine}
import com.pagero.paglab.cache.example.model.Book

import scala.concurrent.duration.DurationInt

object ScaffeineCache {

  val cache: Cache[String, Book] = Scaffeine().
    recordStats().
    expireAfterAccess(1.hour).
    expireAfterWrite(1.hour).
    maximumSize(20).
    build[String, Book]()
}
