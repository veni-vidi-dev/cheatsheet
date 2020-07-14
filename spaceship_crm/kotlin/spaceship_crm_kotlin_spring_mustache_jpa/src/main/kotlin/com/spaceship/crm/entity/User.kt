/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue
        var id: Long,

        @Column(nullable = false, unique = true)
        var username: String,
        var password: String,

        var firstName: String,
        var lastName: String,

        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var userInfo: UserInfo? = null,

        @ManyToMany(fetch = FetchType.LAZY)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
        var roles: List<Role>? = null,

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var dreams: List<Dream>? = null,

        @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var activities: List<UserActivity>? = null
)