package com.spaceship.crm.repository

import org.springframework.stereotype.Repository
import com.spaceship.crm.config.DBConfig.db
import com.spaceship.crm.frm.entity.Role
import com.spaceship.crm.frm.table.UserRoleTable.userRoleQuery
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

@Repository
class RoleRepository {

  def findAllByUserId(userId: Long): Seq[Role] = {
    val userRoles = userRoleQuery
      .filter(_.userId === userId)
      .flatMap(_.role)
    Await.result(db.run(userRoles.result) , 10.seconds )
  }
}
