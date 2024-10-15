package com.pagero.paglab.cache.example.model

import com.pagero.paglab.cache.example.Profile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

trait BookComponent {
  this: Profile =>

  class BookTable(tag: Tag) extends Table[Book](tag, "book") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def title: Rep[String] = column[String]("title")

    def description: Rep[Option[String]] = column[Option[String]]("description")

    def pageCount: Rep[Int] = column[Int]("page_count")

    override def * : ProvenShape[Book] = (id, title, description, pageCount).<>(Book.tupled, Book.unapply)
  }

  val bookQuery = TableQuery[BookTable]
}

//class BookTable(tag: Tag) extends Table[Book](tag, "book") {
//
//  def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
//
//  def title: Rep[String] = column[String]("title")
//
//  def description: Rep[Option[String]] = column[Option[String]]("description")
//
//  def pageCount: Rep[Int] = column[Int]("page_count")
//
//  override def * : ProvenShape[Book] = (id, title, description, pageCount).<>(Book.tupled, Book.unapply)
//}