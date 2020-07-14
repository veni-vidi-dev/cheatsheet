/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import java.util.Date

import javax.persistence._
import org.hibernate.annotations.{OnDelete, OnDeleteAction}

import scala.beans.BeanProperty

@Entity
class Activity {

  @Id
  @GeneratedValue
  @BeanProperty var id: Long = _

  @BeanProperty var name: String = _

  @BeanProperty var startDate: Date = _
  @BeanProperty var endDate: Date = _

  @OneToMany(mappedBy = "activity", cascade = Array(CascadeType.ALL), orphanRemoval = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  var users: java.util.List[UserActivity] = new java.util.ArrayList[UserActivity]
}
