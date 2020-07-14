/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.bean

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.spaceship.crm.entity.Role
import com.spaceship.crm.entity.User
import java.util.stream.Collectors

class CustomUserDetails(
        var user: User
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return user.roles!!.stream()
                .map {role -> SimpleGrantedAuthority(role.name)}
                .collect(Collectors.toSet())
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}