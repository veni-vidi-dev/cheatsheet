package com.spaceship.crm.frm.entity

import scala.beans.BeanProperty

case class UserInfo (
  @BeanProperty id: Long,
  @BeanProperty phone: String,
  @BeanProperty address: String,
  @BeanProperty userId: Long
)
