/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository

import org.springframework.data.repository.CrudRepository
import com.spaceship.crm.entity.{UserActivity, UserActivityId}

trait UserActivityRepository extends CrudRepository[UserActivity, UserActivityId] {

}
