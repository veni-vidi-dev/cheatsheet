/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service

import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import com.spaceship.crm.bean.CustomUserDetails
import com.spaceship.crm.repository.UserRepository
import javax.transaction.Transactional

@Service
class UserService(
        @Autowired private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw AuthenticationServiceException(username)
        Hibernate.initialize(user!!.roles)
        return CustomUserDetails(user!!)
    }
}