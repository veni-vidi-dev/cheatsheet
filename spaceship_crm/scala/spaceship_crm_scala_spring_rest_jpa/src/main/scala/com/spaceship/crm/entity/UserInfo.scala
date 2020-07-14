/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import javax.persistence.{Entity, GeneratedValue, Id, OneToOne}

import scala.beans.BeanProperty

@Entity
class UserInfo {

  @Id
  @GeneratedValue
  @BeanProperty var id: Long = _

  @BeanProperty var phone: String = _
  @BeanProperty var address: String = _

  @OneToOne
  var user: User = _
}
