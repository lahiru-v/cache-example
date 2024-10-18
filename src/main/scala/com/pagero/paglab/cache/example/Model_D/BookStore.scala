package com.pagero.paglab.cache.example.Model_D

import com.pagero.paglab.cache.example.Main.cache
import com.pagero.paglab.cache.example.model.Book

import scala.util.Random

object BookStore {
  private val books = (1 to 10000).map { i =>
    Book(
      id = i,
      title = s"Book_$i",
      description = Option(s"Desc_${Random.nextInt(100)}"),
      pageCount = Random.nextInt(400) + 100,
    )
  }.toList

  private val bookMap: Map[String, Book] = books.map(b => b.title -> b).toMap

  def getBookByName(bookName: String): Option[Book] = {
    cache.get(bookName) match {
      case Some(book) =>
        println(s"Accessing from cache: $bookName")
        Some(book)
      case None =>
        println(s"Accessing from main DB with delay: $bookName")
        Thread.sleep(250)
        bookMap.get(bookName).map { book =>
          cache.add(book)
          book
        }
    }
  }
}
