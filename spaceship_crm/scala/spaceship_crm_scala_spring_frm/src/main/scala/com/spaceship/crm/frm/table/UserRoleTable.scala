package com.spaceship.crm.frm.table

import com.spaceship.crm.frm.entity.UserRole
import com.spaceship.crm.frm.table.UserTable.userQuery
import com.spaceship.crm.frm.table.RoleTable.roleQuery
import slick.jdbc.PostgresProfile.api._

object UserRoleTable {
  lazy val userRoleQuery = TableQuery[UserRoleTable]
}

class UserRoleTable(tag: Tag) extends Table[UserRole](tag, "user_role") {
  def userId = column[Long]("user_id", O.PrimaryKey)
  def roleId = column[Long]("role_id", O.PrimaryKey)

  override def * = (userId, roleId) <> (UserRole.tupled, UserRole.unapply)

  //  def pk = primaryKey("user_activity_pkey", (userId, roleId))
  def user = foreignKey("fkj345gk1bovqvfame88rcx7yyx",userId,userQuery)(_.id)
  def role = foreignKey("fka68196081fvovjhkek5m97n3y",roleId,roleQuery)(_.id)
}
