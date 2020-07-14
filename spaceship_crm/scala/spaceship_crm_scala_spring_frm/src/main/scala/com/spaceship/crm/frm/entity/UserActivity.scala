package com.spaceship.crm.frm.entity

import java.time.LocalDateTime
import java.util.Date

case class UserActivity (
  userId: Long,
  activityId: Long,
  createdAt: LocalDateTime,
  createdBy: Option[Long]
)
