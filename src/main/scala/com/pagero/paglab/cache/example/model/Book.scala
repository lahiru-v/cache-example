package com.pagero.paglab.cache.example.model

case class Book(id: Int,
                title: String,
                description: Option[String],
                pageCount: Int)
