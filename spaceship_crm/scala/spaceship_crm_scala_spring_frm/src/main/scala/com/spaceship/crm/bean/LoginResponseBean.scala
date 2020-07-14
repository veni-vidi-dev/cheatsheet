package com.spaceship.crm.bean

import com.spaceship.crm.frm.entity.User

import scala.beans.BeanProperty

class LoginResponseBean {
  @BeanProperty var user: User = _
  @BeanProperty var token: String = _

  def this(user: User, token: String) {
    this()
    this.user = user
    this.token = token
  }
}