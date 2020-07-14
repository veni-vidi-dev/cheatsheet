package com.spaceship.crm.config

import slick.jdbc.PostgresProfile.api._

object DBConfig {
  lazy val db = Database.forConfig("spaceshipDB")
}
