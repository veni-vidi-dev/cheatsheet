/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
import javax.persistence.*

@Entity
class Activity(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,
        var startDate: Date,
        var endDate: Date,

        @OneToMany(mappedBy = "activity", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OnDelete(action = OnDeleteAction.CASCADE)
        private var users: List<UserActivity>? = null
)