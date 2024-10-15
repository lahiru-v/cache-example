package com.pagero.paglab.cache.example

import com.typesafe.config.ConfigFactory
import slick.jdbc.PostgresProfile.api._

object DatabasePackage {
  private val config = ConfigFactory.load()
  val db: Database = Database.forConfig("database", config)
}
