/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.{AuthenticationManager, AuthenticationServiceException, UsernamePasswordAuthenticationToken}
import org.springframework.stereotype.Service
import com.spaceship.crm.bean.LoginResponseBean
import com.spaceship.crm.entity.User

@Service
class SecurityService(
                     @Autowired private val jwtService: JwtService,
                     @Autowired private val authenticationManager: AuthenticationManager
                     ) {
  def doLogin(user: User): LoginResponseBean = {
    val token = new UsernamePasswordAuthenticationToken(
      user.username,
      user.password
    )

    val auth = authenticationManager.authenticate(token)

    if (auth == null || !auth.isAuthenticated)
      throw new AuthenticationServiceException("Authentication exception")

    new LoginResponseBean(
      auth.getPrincipal.asInstanceOf[CustomUserDetails].user,
      jwtService.generateToken(auth)
    )
  }
}
