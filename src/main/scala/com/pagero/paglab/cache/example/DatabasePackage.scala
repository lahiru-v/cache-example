package com.pagero.paglab.cache.example

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import slick.jdbc.PostgresProfile.api._

object DatabasePackage {
  private val config = ConfigFactory.load()
  private val maxPoolSize = config.getInt("db.max_pool_size")

  private val hikariConfig = new HikariConfig()
  hikariConfig.setJdbcUrl(config.getString("db.url"))
  hikariConfig.setUsername(config.getString("db.user"))
  hikariConfig.setPassword(config.getString("db.password"))
  hikariConfig.setDriverClassName(config.getString("db.driver"))
  hikariConfig.addDataSourceProperty("cachePrepStmts", "true")
  hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250")
  hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

  private val hikariDataSource = new HikariDataSource(hikariConfig)

  val c3p0DataSource = new ComboPooledDataSource()
  new ComboPooledDataSource()
  c3p0DataSource.setJdbcUrl(config.getString("db.url"))
  c3p0DataSource.setUser(config.getString("db.user"))
  c3p0DataSource.setPassword(config.getString("db.password"))
  c3p0DataSource.setDriverClass(config.getString("db.driver"))

  val db: Database = Database.forDataSource(c3p0DataSource, Some(maxPoolSize))
}
