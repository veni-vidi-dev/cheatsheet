/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import java.util

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonInclude, JsonProperty}
import javax.persistence._
import org.hibernate.annotations.{OnDelete, OnDeleteAction}

import scala.beans.BeanProperty

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
class User {

  @Id
  @GeneratedValue
  @BeanProperty var id: Long = _

  @Column(nullable = false, unique = true)
  @BeanProperty var username: String = _

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @BeanProperty var password: String = _


  @BeanProperty var firstName: String = _
  @BeanProperty var lastName: String = _

  @OneToOne(
    mappedBy = "user",
    fetch = FetchType.LAZY,
    cascade = Array(CascadeType.ALL),
    orphanRemoval = true
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  @BeanProperty var userInfo: UserInfo = _

  @ManyToMany(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinTable(
    name = "user_role",
    joinColumns = Array(new JoinColumn(name = "user_id")),
    inverseJoinColumns = Array(new JoinColumn(name = "role_id"))
  )
  @BeanProperty var roles: java.util.List[Role] = new util.ArrayList[Role]

  @OneToMany(
    mappedBy = "user",
    fetch = FetchType.LAZY,
    cascade = Array(CascadeType.ALL),
    orphanRemoval = true
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  @BeanProperty var dreams: java.util.List[Dream] = new util.ArrayList[Dream]

  @OneToMany(
    mappedBy = "user",
    cascade = Array(CascadeType.ALL),
    orphanRemoval = true
  )
  @OnDelete(action = OnDeleteAction.CASCADE)
  var activities: java.util.List[UserActivity] = new util.ArrayList[UserActivity]
}
