package com.pagero.paglab.cache.example.tinylfu

import org.ehcache.Cache
import org.ehcache.event.{CacheEvent, CacheEventListener, EventType}

import scala.collection.mutable

class TinyLFUEventListener[K, V](policy: TinyLFUEvictionPolicy[K], cache: Cache[K, V]) extends CacheEventListener[K, V] {
  private val keySet: mutable.Set[K] = mutable.Set.empty

  override def onEvent(event: CacheEvent[_ <: K, _ <: V]): Unit = {
    event.getType match {
      case EventType.CREATED | EventType.UPDATED =>
        policy.onAccess(event.getKey)
        keySet.add(event.getKey)

        // Evict if the size exceeds the limit
        if (keySet.size > policy.maxSize) {
          policy.findEvictionCandidate(keySet.toSeq).foreach { evictionKey =>
            keySet.remove(evictionKey)
            cache.remove(evictionKey)
          }
        }

      case EventType.REMOVED =>
        keySet.remove(event.getKey)

      case _ => // Ignore other events
    }
  }
}