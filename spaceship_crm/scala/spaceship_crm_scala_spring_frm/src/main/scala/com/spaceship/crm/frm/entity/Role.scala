package com.spaceship.crm.frm.entity

import scala.beans.BeanProperty

case class Role (
  @BeanProperty id: Long,
  @BeanProperty name: String
)
