/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository

import java.util.Optional

import org.springframework.data.repository.CrudRepository
import com.spaceship.crm.entity.User

trait UserRepository extends CrudRepository[User, Long] {
  def findByUsername(username: String): Optional[User]
}
