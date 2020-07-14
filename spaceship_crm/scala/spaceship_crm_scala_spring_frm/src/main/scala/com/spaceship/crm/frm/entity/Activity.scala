package com.spaceship.crm.frm.entity

import java.time.LocalDateTime

import scala.beans.BeanProperty

case class Activity (
  @BeanProperty id: Long,
  @BeanProperty name: String,
  @BeanProperty startDate: LocalDateTime,
  @BeanProperty endDate: LocalDateTime
)
