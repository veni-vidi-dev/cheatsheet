package com.spaceship.crm.bean

import java.time.LocalDateTime
import java.util.Date

import scala.beans.BeanProperty


class UserActivityBean {
  @BeanProperty var userId: Long = _
  @BeanProperty var firstName: String = _
  @BeanProperty var lastName: String = _
  @BeanProperty var activityName: String = _
  @BeanProperty var start: LocalDateTime = _
  @BeanProperty var end: LocalDateTime = _
  @BeanProperty var added: LocalDateTime = _

  def this(userId: Long,
           firstName: String,
           lastName: String,
           activityName: String,
           start: LocalDateTime,
           end: LocalDateTime,
           added: LocalDateTime) {
    this()
    this.userId = userId
    this.firstName = firstName
    this.lastName = lastName
    this.activityName = activityName
    this.start = start
    this.end = end
    this.added = added
  }
}