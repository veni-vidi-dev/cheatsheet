/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import com.spaceship.crm.bean.CustomUserDetails
import com.spaceship.crm.bean.UserLoginBean
import com.spaceship.crm.entity.User

@Service
class SecurityService(
        @Autowired private val userService: UserService,
        @Autowired private val authenticationManager: AuthenticationManager
) {

    fun doLogin(user: UserLoginBean): User? {
        val token = UsernamePasswordAuthenticationToken(user.username, user.password)
        val auth = authenticationManager.authenticate(token)
        if (auth.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = auth
            return (auth.principal as CustomUserDetails).user
        }
        return null
    }

    fun doLogout() {
        SecurityContextHolder.getContext().authentication = null
    }


}