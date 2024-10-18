package com.pagero.paglab.cache.example.Model_D

import com.pagero.paglab.cache.example.model.Book

import scala.collection.mutable

case class Cache(capacity: Int) {
  private val cache = mutable.LinkedHashSet[String]()  // Stores book names in a LinkedHashSet for LRU behavior
  private val bookData = mutable.Map[String, Book]()   // Maps book names to Book objects
  def contains(bookName: String): Boolean = cache.contains(bookName)

  def get(bookName: String): Option[Book] = bookData.get(bookName)

  def add(book: Book): Unit = {
    //Here get the ML model result so that we check that the book should be added to the cache or not

    if (cache.size >= capacity) {
      // Evict the least recently used book
      val lruBook = cache.head
      cache.remove(lruBook)
      bookData.remove(lruBook)
    }
    cache.add(book.title)
    bookData(book.title) = book
  }
}
