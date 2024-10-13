package com.pagero.paglab.cache.example;

object Main extends App {
  println("Hello world...")

  FlywayMigration.migrate()


  println("End of the application....")
}
