package com.spaceship.crm.security

import java.util.Date

import io.jsonwebtoken.{Jwts, SignatureAlgorithm}
import javax.security.sasl.AuthenticationException
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

object JwtService {
  private val LOGGER = LoggerFactory.getLogger(classOf[JwtService])
  private val JWT_SECRET = "some.jwt.secret"
  private val JWT_EXPIRATION_MS = 86400000
}

@Service
class JwtService {

  def generateToken(authentication: Authentication): String = {
    val userPrincipal = authentication.getPrincipal.asInstanceOf[UserDetails]
    Jwts.builder
      .setSubject(userPrincipal.getUsername)
      .setIssuedAt(new Date)
      .setExpiration(new Date(System.currentTimeMillis + JwtService.JWT_EXPIRATION_MS))
      .signWith(SignatureAlgorithm.HS512, JwtService.JWT_SECRET)
      .compact
  }

  @throws[AuthenticationException]
  def validateTokenAndGetId(authToken: String): String = {
    try {
      Jwts.parser
        .setSigningKey(JwtService.JWT_SECRET)
        .parseClaimsJws(authToken)
        .getBody
        .getSubject
    } catch {
      case throwable: Throwable =>  {
        JwtService.LOGGER.error("JWT exception: " + throwable)
        throw new AuthenticationServiceException(throwable.getMessage)
      }
    }
  }

}
