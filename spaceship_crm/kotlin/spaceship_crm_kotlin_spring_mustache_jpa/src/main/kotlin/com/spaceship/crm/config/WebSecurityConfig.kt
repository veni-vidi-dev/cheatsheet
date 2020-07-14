/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.spaceship.crm.service.UserService

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        @Autowired private val userService: UserService
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/login","/error").permitAll()
                .antMatchers("/addusertoactivity").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(userService)
                .passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}