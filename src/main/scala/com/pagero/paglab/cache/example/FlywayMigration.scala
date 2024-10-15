package com.pagero.paglab.cache.example

import com.typesafe.config.ConfigFactory
import org.flywaydb.core.Flyway

object FlywayMigration {

  def migrate(): Unit = {
    val config = ConfigFactory.load()
    val dbUrl = config.getString("db.url")
    val dbUser = config.getString("db.user")
    val dbPassword = config.getString("db.password")

    Flyway.configure().dataSource(dbUrl, dbUser, dbPassword).load().migrate()
  }
}
