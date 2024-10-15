package com.pagero.paglab.cache.example

import slick.jdbc.{JdbcProfile, PostgresProfile}

trait Profile {
  lazy val profile: JdbcProfile = PostgresProfile
}
