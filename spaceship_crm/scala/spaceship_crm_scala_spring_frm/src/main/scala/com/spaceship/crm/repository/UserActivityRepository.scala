package com.spaceship.crm.repository

import java.time.LocalDateTime

import org.springframework.stereotype.Repository
import com.spaceship.crm.bean.UserActivityBean
import com.spaceship.crm.config.DBConfig.db
import com.spaceship.crm.frm.entity.{Role, UserActivity}
import com.spaceship.crm.frm.table.UserActivityTable.userActivityQuery
import com.spaceship.crm.frm.table.UserTable.userQuery
import com.spaceship.crm.frm.table.ActivityTable.activityQuery
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global

@Repository
class UserActivityRepository {

  /**
    * query:
    *     select u.id, u.first_name, u.last_name, a.name, a.start_date, a.end_date, ua.created_at
    *     from activity a
    *       left join user_activity ua on a.id = ua.activity_id
    *       left join users u on ua.user_id = u.id
    *
    * @return
    */
  def findAll(): Seq[UserActivityBean] = {
    val userActivitiesQuery =
      activityQuery
        .join(userActivityQuery).on(_.id === _.activityId)
        .join(userQuery).on(_._2.userId === _.id)

    val action = {
      for {
        userActivityResult <- userActivitiesQuery.result
      } yield {
        userActivityResult.map { row =>
            new UserActivityBean(
              row._2.id,
              row._2.firstName,
              row._2.lastName,
              row._1._1.name,
              row._1._1.startDate,
              row._1._1.endDate,
              row._1._2.createdAt
            )
        }
      }
    }
    Await.result(db.run(action) , 10.seconds)
  }

  def insert(activityId: Long, userId: Long): Unit = {
    val insertUserActivity = userActivityQuery += UserActivity(userId, activityId, LocalDateTime.now(), None)
    Await.result(db.run(insertUserActivity) , 10.seconds)
  }

  def delete(activityId: Long, userId: Long): Unit = {
    val deleteByIdQuery =
      userActivityQuery
        .filter(ua => ua.userId === userId && ua.activityId === activityId)
          .delete
    Await.result(db.run(deleteByIdQuery) , 10.seconds)
  }
}
