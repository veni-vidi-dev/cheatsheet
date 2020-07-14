/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package repository

import config.DBConfig.db
import frm.entity.Activity
import frm.table.ActivityTable.activityQuery
import javax.inject.{Inject, Singleton}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent._

@Singleton
class ActivityRepository @Inject() ()(implicit ec: ExecutionContext){

  def findAll(): Future[Seq[Activity]] = {
    db.run(activityQuery.result)
  }

  def findById(id: Long): Future[Option[Activity]] = {
    val activityByIdQuery = activityQuery.filter(_.id === id)
    db.run(activityByIdQuery.result).map(_.headOption)
  }

}
