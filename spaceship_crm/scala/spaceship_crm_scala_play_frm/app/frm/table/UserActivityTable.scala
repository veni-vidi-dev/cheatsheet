/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.table

import java.sql.Timestamp
import java.time.LocalDateTime

import frm.entity.UserActivity
import slick.jdbc.PostgresProfile.api._

import UserTable.userQuery
import ActivityTable.activityQuery

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

  def user = foreignKey("fks41is1raa3f0y5q5g0pw2rfd4",userId,userQuery)(_.id)
  def activity = foreignKey("fklw9o1xb2ki2hnwq1o3kk5dlja",activityId,activityQuery)(_.id)
}
