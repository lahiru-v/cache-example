package com.pagero.paglab.cache.example.tinylfu

class TinyLFUEvictionPolicy[K](val maxSize: Int) {
  private val frequencySketch: FrequencySketch[K] = new FrequencySketch[K](maxSize)

  // Increment frequency for an accessed key
  def onAccess(key: K): Unit = frequencySketch.increment(key)

  def findEvictionCandidate(keys: Seq[K]): Option[K] = {
    if (keys.nonEmpty) Some(keys.minBy(frequencySketch.estimateFrequency))
    else None
  }
}
