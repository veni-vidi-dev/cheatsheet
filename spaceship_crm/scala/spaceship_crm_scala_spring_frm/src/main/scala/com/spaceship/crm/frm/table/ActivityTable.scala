package com.spaceship.crm.frm.table

import java.sql.Timestamp
import java.time.LocalDateTime

import com.spaceship.crm.frm.entity.Activity
import slick.jdbc.PostgresProfile.api._

object ActivityTable {
  implicit val localDateTimeColumnType: BaseColumnType[LocalDateTime] = MappedColumnType.base[LocalDateTime, Timestamp](Timestamp.valueOf, _.toLocalDateTime)
  lazy val activityQuery = TableQuery[ActivityTable]
}

class ActivityTable(tag: Tag) extends Table[Activity](tag, "activity") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def startDate = column[LocalDateTime]("start_date")
  def endDate = column[LocalDateTime]("end_date")

  override def * = (id, name, startDate, endDate) <> (Activity.tupled, Activity.unapply)

}
