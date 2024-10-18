//package com.pagero.paglab.cache.example
//
//import com.pagero.paglab.cache.example.Model_D.{BookStore, Cache, Model, Request}
//import com.pagero.paglab.cache.example.model.Book
//
//import scala.util.Random
//
//object Main extends App {
//  val cache = Cache(100)
//  val books = (1 to 10000).map { i =>
//    Book(
//      id = i,
//      title = s"Book_$i",
//      description = Option(s"desc_${Random.nextInt(100)}"),
//      pageCount = Random.nextInt(400) + 100, // Pages between 100 and 500
//    )
//  }.toList
//
//  val requests = (1 to 1000).map { _ =>
//    val randomBook = books(Random.nextInt(books.size))
//    Request(randomBook.title, randomBook, System.currentTimeMillis())
//  }.toList
//
//  Model.trainAgent(requests)
//
//  val bookName = "Book_101"
//  val shouldCache = Model.predict(bookName)
//  println(s"Should cache '$bookName'? $shouldCache")
//}