/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import java.util.Date

import javax.persistence._

@Entity
@IdClass(classOf[UserActivityId])
class UserActivity {

  @Id
  @ManyToOne
  var user: User = _

  @Id
  @ManyToOne
  var activity: Activity = _

  @Column(updatable = false)
  var createdAt: Date = _

  @Column(updatable = false)
  var createdBy: java.lang.Long = _
}

class UserActivityId extends Serializable {
  var user: Long = _
  var activity: Long = _

  def this(user: Long, activity: Long) {
    this()
    this.user = user
    this.activity = activity
  }
}
