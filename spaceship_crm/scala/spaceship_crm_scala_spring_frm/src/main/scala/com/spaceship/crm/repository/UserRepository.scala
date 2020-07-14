package com.spaceship.crm.repository

import org.springframework.stereotype.Repository
import com.spaceship.crm.frm.entity.User
import com.spaceship.crm.frm.table.UserTable.userQuery
import com.spaceship.crm.config.DBConfig.db
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global

@Repository
class UserRepository {

  def findById(id: Long): Option[User] = {
    val userByIdQuery = userQuery.filter(_.id === id)
    Await.result(db.run(userByIdQuery.result).map(_.headOption) , 10.seconds )
  }

  def findByUsername(username: String): Option[User] = {
    val userByUsernameQuery = userQuery.filter(_.username === username)
    Await.result(db.run(userByUsernameQuery.result).map(_.headOption) , 10.seconds )
  }

}
