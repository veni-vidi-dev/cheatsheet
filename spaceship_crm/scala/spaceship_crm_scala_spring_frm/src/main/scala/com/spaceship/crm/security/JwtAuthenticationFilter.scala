package com.spaceship.crm.security

import javax.servlet.FilterChain
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import com.spaceship.crm.service.UserService

@Component
class JwtAuthenticationFilter(
                               @Autowired private val jwtService: JwtService,
                               @Autowired private val userService: UserService
                             ) extends OncePerRequestFilter {

  override def doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain): Unit = {
    try {
      val jwt = getJwtFromRequest(request)
      if (jwt.nonEmpty) {
        val user = jwtService.validateTokenAndGetId(jwt.get)
        val userDetails = userService.loadUserByUsername(user)
        val authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities)
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request))
        SecurityContextHolder.getContext.setAuthentication(authentication)
      }
    } catch {
      case ex: AuthenticationException =>
        response.setHeader("Content-Type", "application/json")
        response.setStatus(401)
        response.getOutputStream.write("{\"error\":\"401\"}".getBytes)
        return
      case ex: Exception =>
        response.setHeader("Content-Type", "application/json")
        response.setStatus(500)
        response.getOutputStream.write("{\"error\":\"500\",\"description\":\"User authentication failed\"}".getBytes)
        return
    }
    filterChain.doFilter(request, response)
  }

  private def getJwtFromRequest(request: HttpServletRequest): Option[String] = {
    val bearerToken = request.getHeader("Authorization")
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      Option(bearerToken.substring(7, bearerToken.length))
    } else {
      Option.empty
    }
  }
}
