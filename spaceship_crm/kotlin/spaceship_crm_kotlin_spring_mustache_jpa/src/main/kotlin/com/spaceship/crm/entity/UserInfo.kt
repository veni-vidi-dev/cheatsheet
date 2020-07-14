/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class UserInfo(
        @Id
        @GeneratedValue
        var id: Long,
        var phone: String? = null,
        var address: String? = null,

        @OneToOne
        var user: User? = null
)