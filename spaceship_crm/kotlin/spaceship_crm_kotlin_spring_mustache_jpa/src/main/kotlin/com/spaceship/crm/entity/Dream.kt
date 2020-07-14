/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import javax.persistence.*

@Entity
class Dream(
        @Id
        @GeneratedValue
        var id: Long,
        var description: String,
        @ManyToOne(fetch = FetchType.LAZY)
        var user: User? = null
)