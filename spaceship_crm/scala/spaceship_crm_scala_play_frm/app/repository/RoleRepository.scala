/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package repository

import config.DBConfig.db
import frm.entity.Role
import frm.table.UserRoleTable.userRoleQuery
import javax.inject.{Inject, Singleton}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RoleRepository @Inject() ()(implicit ec: ExecutionContext) {

  def findAllByUserId(userId: Long): Future[Seq[Role]] = {
    val userRoles = userRoleQuery
      .filter(_.userId === userId)
      .flatMap(_.role)
    db.run(userRoles.result)
  }
}
