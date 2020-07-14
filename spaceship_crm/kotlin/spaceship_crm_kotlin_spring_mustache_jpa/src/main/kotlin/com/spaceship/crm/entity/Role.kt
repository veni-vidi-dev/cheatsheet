/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
class Role(
        @Id
        @GeneratedValue
        var id: Long,
        var name: String,

        @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var users: List<User>? = null
)