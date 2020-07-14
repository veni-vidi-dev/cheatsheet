/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.security

import java.util

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.spaceship.crm.entity.User

import scala.collection.JavaConverters._

class CustomUserDetails(
                         var user: User
                       ) extends UserDetails {



//  .asScala - creates wrapper over collection/array,
// .asJava retrieves the same link
  override def getAuthorities: util.Collection[_ <: GrantedAuthority] =
    user.roles
      .asScala
      .map(role => new SimpleGrantedAuthority(role.name))
      .asJava

  override def getPassword: String = user.password

  override def getUsername: String = user.username

  override def isAccountNonExpired: Boolean = true

  override def isAccountNonLocked: Boolean = true

  override def isCredentialsNonExpired: Boolean = true

  override def isEnabled: Boolean = true
}
