package com.spaceship.crm.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService}
import org.springframework.stereotype.Service
import com.spaceship.crm.frm.entity.User
import com.spaceship.crm.repository.{RoleRepository, UserRepository}
import com.spaceship.crm.security.CustomUserDetails


@Service
class UserService(
                   @Autowired private val userRepository: UserRepository,
                   @Autowired private val roleRepository: RoleRepository
                 ) extends UserDetailsService {

  @throws[AuthenticationException]
  override def loadUserByUsername(s: String): UserDetails = {
    val optionalUser: Option[User] = userRepository.findByUsername(s)
    if (optionalUser.isEmpty) throw new AuthenticationServiceException(s)
    val user: User = optionalUser.get
    user.roles = roleRepository.findAllByUserId(user.getId)
    new CustomUserDetails(user)
  }

}
