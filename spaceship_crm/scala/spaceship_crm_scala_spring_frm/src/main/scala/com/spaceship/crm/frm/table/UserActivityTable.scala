package com.spaceship.crm.frm.table

import java.sql.Timestamp
import java.time.LocalDateTime

import com.spaceship.crm.frm.entity.UserActivity
import com.spaceship.crm.frm.table.UserTable.userQuery
import com.spaceship.crm.frm.table.ActivityTable.activityQuery
import slick.jdbc.PostgresProfile.api._

object UserActivityTable {
  implicit val localDateTimeColumnType: BaseColumnType[LocalDateTime] = MappedColumnType.base[LocalDateTime, Timestamp](Timestamp.valueOf, _.toLocalDateTime)
  lazy val userActivityQuery = TableQuery[UserActivityTable]
}

class UserActivityTable(tag: Tag) extends Table[UserActivity](tag, "user_activity") {
  def userId = column[Long]("user_id", O.PrimaryKey)
  def activityId = column[Long]("activity_id", O.PrimaryKey)
  def createdAt = column[LocalDateTime]("created_at")
  def createdBy = column[Option[Long]]("created_by")

  override def * = (userId, activityId, createdAt, createdBy) <> (UserActivity.tupled, UserActivity.unapply)

//  as an option instead column[Long]("user_id", O.PrimaryKey)
//  def pk = primaryKey("user_activity_pkey", (userId, activityId))

  def user = foreignKey("fks41is1raa3f0y5q5g0pw2rfd4",userId,userQuery)(_.id)
  def activity = foreignKey("fklw9o1xb2ki2hnwq1o3kk5dlja",activityId,activityQuery)(_.id)
}
