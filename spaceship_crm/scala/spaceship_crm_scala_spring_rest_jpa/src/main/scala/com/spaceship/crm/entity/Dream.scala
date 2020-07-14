/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import javax.persistence._

import scala.beans.BeanProperty

@Entity
class Dream {

  @Id
  @GeneratedValue
  @BeanProperty var id: Long = _

  @BeanProperty var description: String = _

  @ManyToOne(fetch = FetchType.LAZY)
  var user: User = _
}
