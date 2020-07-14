/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository

import org.springframework.data.repository.CrudRepository
import com.spaceship.crm.entity.Activity

trait ActivityRepository extends CrudRepository[Activity, Long] {

}
