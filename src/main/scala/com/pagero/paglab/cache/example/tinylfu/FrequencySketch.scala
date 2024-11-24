package com.pagero.paglab.cache.example.tinylfu

class FrequencySketch[K](size: Int) {
  private val counts = Array.fill(size)(0)

  private def hash(key: K): Int = key.hashCode().abs % size

  def increment(key: K): Unit = {
    counts(hash(key)) += 1
  }

  def estimateFrequency(key: K): Int = {
    counts(hash(key))
  }
}
