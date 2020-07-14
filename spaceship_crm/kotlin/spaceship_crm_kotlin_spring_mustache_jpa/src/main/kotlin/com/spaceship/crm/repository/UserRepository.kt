/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository

import org.springframework.data.repository.CrudRepository
import com.spaceship.crm.entity.User

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}