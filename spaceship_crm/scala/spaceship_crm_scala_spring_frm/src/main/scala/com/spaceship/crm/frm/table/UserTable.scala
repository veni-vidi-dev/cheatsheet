package com.spaceship.crm.frm.table

import com.spaceship.crm.frm.entity.User
import slick.jdbc.PostgresProfile.api._

object UserTable {
  lazy val userQuery = TableQuery[UserTable]
}

class UserTable(tag: Tag) extends Table[User](tag, "users")  {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("username")
  def password = column[String]("password")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")

  override def * = (id, username, password, firstName, lastName) <> (User.tupled, User.unapply)
}
