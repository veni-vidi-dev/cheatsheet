/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import javax.persistence._
import org.hibernate.annotations.{OnDelete, OnDeleteAction}

import scala.beans.BeanProperty

@Entity
class Role {

  @Id
  @GeneratedValue
  @BeanProperty var id: Long = _

  @BeanProperty var name: String = _

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  var users: java.util.List[User] = new java.util.ArrayList[User]
}
