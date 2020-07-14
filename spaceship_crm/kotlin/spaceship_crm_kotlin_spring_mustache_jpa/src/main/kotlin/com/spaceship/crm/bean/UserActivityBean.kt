/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.bean

import java.util.*

class UserActivityBean(
        var userId: Long? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var activityId: Long? = null,
        var activityName: String? = null,
        var start: Date? = null,
        var end: Date? = null,
        var added: Date? = null
)