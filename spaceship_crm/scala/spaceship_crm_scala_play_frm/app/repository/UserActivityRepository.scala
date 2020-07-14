/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package repository

import java.time.LocalDateTime

import bean.UserActivityBean
import config.DBConfig.db
import frm.entity.UserActivity
import frm.table.ActivityTable.activityQuery
import frm.table.UserActivityTable.userActivityQuery
import frm.table.UserTable.userQuery
import javax.inject.{Inject, Singleton}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent._

@Singleton
class UserActivityRepository @Inject() ()(implicit ec: ExecutionContext) {

  /**
    * query:
    *     select u.id, u.first_name, u.last_name, a.name, a.start_date, a.end_date, ua.created_at
    *     from activity a
    *       left join user_activity ua on a.id = ua.activity_id
    *       left join users u on ua.user_id = u.id
    *
    * @return
    */
  def findAll(): Future[Seq[UserActivityBean]] = {
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
    db.run(action)
  }

  def insert(activityId: Long, userId: Long): Future[Int] = {
    val insertUserActivity = userActivityQuery += UserActivity(userId, activityId, LocalDateTime.now(), None)
    db.run(insertUserActivity)
  }

  def delete(activityId: Long, userId: Long): Future[Int] = {
    val deleteByIdQuery =
      userActivityQuery
        .filter(ua => ua.userId === userId && ua.activityId === activityId)
          .delete
    db.run(deleteByIdQuery)
  }
}
