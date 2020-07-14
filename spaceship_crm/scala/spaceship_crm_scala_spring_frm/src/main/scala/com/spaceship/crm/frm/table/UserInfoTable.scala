package com.spaceship.crm.frm.table

import com.spaceship.crm.frm.entity.UserInfo
import com.spaceship.crm.frm.table.UserTable.userQuery
import slick.jdbc.PostgresProfile.api._

object UserInfoTable {
  lazy val userInfoQuery = TableQuery[UserInfoTable]
}

//class ActivityTable(tag: Tag) extends Table[Activity](tag, "activity") {
class UserInfoTable(tag: Tag) extends Table[UserInfo](tag, "user_info") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def address = column[String]("address")
  def phone = column[String]("phone")
  def userId = column[Long]("user_id")

  override def * = (id, address, phone, userId) <> (UserInfo.tupled, UserInfo.unapply)

  def user = foreignKey("user",userId,userQuery)(_.id)

}
