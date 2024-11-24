package com.pagero.paglab.cache.example

class HitRates {
  private var hitRate: Int = 0
  private var missRate: Int = 0


  def missed(): Unit = missRate += 1
  def hit(): Unit = hitRate += 1

  override def toString: String = s"hitRate: $hitRate  missRate: $missRate"
}
