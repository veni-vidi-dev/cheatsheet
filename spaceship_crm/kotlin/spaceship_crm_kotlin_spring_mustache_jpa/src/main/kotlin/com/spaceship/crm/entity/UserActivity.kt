/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@IdClass(UserActivityId::class)
class UserActivity(
        @Id
        @ManyToOne
        val user: User,

        @Id
        @ManyToOne
        val activity: Activity,

        @CreatedDate
        @Column(updatable = false)
        val createdAt: Date,

        @CreatedBy
        @Column(updatable = false)
        val createdBy: Long? = null
)

class UserActivityId(
        var user: Long? = null,
        var activity: Long? = null
) : Serializable