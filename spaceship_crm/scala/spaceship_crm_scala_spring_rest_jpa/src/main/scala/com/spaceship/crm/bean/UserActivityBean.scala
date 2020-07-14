/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.bean

import java.util.Date

import scala.beans.BeanProperty


class UserActivityBean {
  @BeanProperty var userId: Long = _
  @BeanProperty var firstName: String = _
  @BeanProperty var lastName: String = _
  @BeanProperty var activityName: String = _
  @BeanProperty var start: Date = _
  @BeanProperty var end: Date = _
  @BeanProperty var added: Date = _

  def this(userId: Long,
           firstName: String,
           lastName: String,
           activityName: String,
           start: Date,
           end: Date,
           added: Date) {
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