/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package frm.table

import frm.entity.UserRole
import slick.jdbc.PostgresProfile.api._
import UserTable.userQuery
import RoleTable.roleQuery

object UserRoleTable {
  lazy val userRoleQuery = TableQuery[UserRoleTable]
}

class UserRoleTable(tag: Tag) extends Table[UserRole](tag, "user_role") {
  def userId = column[Long]("user_id", O.PrimaryKey)
  def roleId = column[Long]("role_id", O.PrimaryKey)

  override def * = (userId, roleId) <> (UserRole.tupled, UserRole.unapply)

  def user = foreignKey("fkj345gk1bovqvfame88rcx7yyx",userId,userQuery)(_.id)
  def role = foreignKey("fka68196081fvovjhkek5m97n3y",roleId,roleQuery)(_.id)
}
