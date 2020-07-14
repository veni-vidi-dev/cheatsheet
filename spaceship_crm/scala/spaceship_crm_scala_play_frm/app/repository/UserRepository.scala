/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package repository

import bean.UserActivityBean
import config.DBConfig.db
import frm.entity.{Role, User}
import frm.table.ActivityTable.activityQuery
import frm.table.UserActivityTable.userActivityQuery
import frm.table.UserTable.userQuery
import frm.table.UserRoleTable.userRoleQuery
import frm.table.RoleTable.roleQuery
import javax.inject.{Inject, Singleton}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent._

@Singleton
class UserRepository @Inject() ()(implicit ec: ExecutionContext) {

  def findAll(): Future[Seq[User]] = {
    db.run(userQuery.result)
  }

  def findById(id: Long): Future[Option[User]] = {
    val userByIdQuery = userQuery.filter(_.id === id)
    db.run(userByIdQuery.result).map(_.headOption)
  }

  def findByUsername(username: String): Future[Option[User]] = {
    val findUserQuery = userQuery.filter(_.username === username)
    val action = {
      for {
        user <- findUserQuery
        userRole <- userRoleQuery if userRole.userId === user.id
        role <- roleQuery if role.id === userRole.roleId
      } yield (user, role.id, role.name)
    }.result
    db.run(action).map { tuples =>
      tuples.groupBy(_._1).map {
        case (user,roles) => {
          user.roles = roles.map(item => Role(item._2, item._3))
          user
        }
      }
    }.map(_.headOption)
  }

  def findByUsername2(username: String): Future[Option[User]] = {
    val findUserWithRolesQuery = userQuery.filter(_.username === username)
      .join(userRoleQuery).on(_.id === _.userId)
      .join(roleQuery).on(_._2.roleId === _.id)
    val action = {
      for {
        ((user, userRole), role) <- findUserWithRolesQuery
      } yield (user, role.id, role.name)
    }.result
    db.run(action).map { tuples =>
      tuples.groupBy(_._1).map {
        case (user,roles) => {
          user.roles = roles.map(item => Role(item._2, item._3))
          user
        }
      }
    }.map(_.headOption)
  }

}
