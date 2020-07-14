package com.spaceship.crm.repository

import org.springframework.stereotype.Repository
import com.spaceship.crm.config.DBConfig.db
import com.spaceship.crm.frm.entity.Activity
import com.spaceship.crm.frm.table.ActivityTable.activityQuery
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global

@Repository
class ActivityRepository {

  def findById(id: Long): Option[Activity] = {
    val activityByIdQuery = activityQuery.filter(_.id === id)
    Await.result(db.run(activityByIdQuery.result).map(_.headOption) , 10.seconds )
  }

}
