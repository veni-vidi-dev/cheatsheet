package com.spaceship.crm.frm.entity

import scala.beans.BeanProperty

case class Dream (
  @BeanProperty id: Long,
  @BeanProperty description: String,
  @BeanProperty userId: Long
)
