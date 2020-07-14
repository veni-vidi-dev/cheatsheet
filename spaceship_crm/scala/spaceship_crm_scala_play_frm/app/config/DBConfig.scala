/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package config

import slick.jdbc.PostgresProfile.api._

object DBConfig {
  lazy val db = Database.forConfig("db")
}
