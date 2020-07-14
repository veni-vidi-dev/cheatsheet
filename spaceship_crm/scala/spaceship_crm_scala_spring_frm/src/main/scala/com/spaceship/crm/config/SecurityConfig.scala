package com.spaceship.crm.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.{HttpStatusEntryPoint, UsernamePasswordAuthenticationFilter}
import com.spaceship.crm.security.JwtAuthenticationFilter
import com.spaceship.crm.service.UserService

@Configuration
@EnableWebSecurity
class SecurityConfig(
                      @Autowired private val userService: UserService,
                      @Autowired private val jwtAuthenticationFilter: JwtAuthenticationFilter
                    ) extends WebSecurityConfigurerAdapter{

  @Bean(Array(BeanIds.AUTHENTICATION_MANAGER))
  @throws[Exception]
  override def authenticationManagerBean: AuthenticationManager = super.authenticationManagerBean

  @throws[Exception]
  override protected def configure(auth: AuthenticationManagerBuilder): Unit = {
    auth.
      userDetailsService(userService)
      .passwordEncoder(new BCryptPasswordEncoder)
  }

  @throws[Exception]
  override protected def configure(http: HttpSecurity): Unit = {
    http.cors.and.csrf.disable
      .exceptionHandling
      .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
      .and
      .sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and
      .authorizeRequests
        .antMatchers("/rest/login").permitAll
        .antMatchers("/rest/activities").authenticated
        .antMatchers("/rest/activity/**").hasRole("ADMIN")
        .anyRequest.authenticated
    http.addFilterBefore(jwtAuthenticationFilter, classOf[UsernamePasswordAuthenticationFilter])
  }
}
