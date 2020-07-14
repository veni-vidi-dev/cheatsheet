package com.spaceship.crm.frm.entity

import com.fasterxml.jackson.annotation.JsonInclude

import scala.beans.BeanProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
case class User(
  @BeanProperty id: Long,
  @BeanProperty username: String,
  password: String,
  @BeanProperty firstName: String,
  @BeanProperty lastName: String
) {

  @BeanProperty var userInfo: UserInfo = _

  @BeanProperty var roles: Seq[Role] = Seq()

  @BeanProperty var dreams: Seq[Dream] = Seq()
}
