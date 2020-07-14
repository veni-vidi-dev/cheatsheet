/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service

import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService, UsernameNotFoundException}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.spaceship.crm.repository.{RoleRepository, UserRepository}
import com.spaceship.crm.security.CustomUserDetails

@Service
class UserService(
                   @Autowired private val userRepository: UserRepository,
                   @Autowired private val roleRepository: RoleRepository
                 ) extends UserDetailsService {

  @Transactional(readOnly = true)
  @throws[AuthenticationException]
  override def loadUserByUsername(s: String): UserDetails = {
    val user = userRepository.findByUsername(s)
    if (!user.isPresent) throw new UsernameNotFoundException(s)
    Hibernate.initialize(user.get.roles)
    new CustomUserDetails(user.get)
  }

}
