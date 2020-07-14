/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import java.util.Date

import io.jsonwebtoken.{Jwts, SignatureAlgorithm}
import javax.inject.Singleton
import javax.security.sasl.AuthenticationException
import org.slf4j.LoggerFactory
import play.api.mvc.{Headers, Request}

trait Logged {
  val LOGGER = LoggerFactory.getLogger(getClass)
}

object JwtService extends Logged {
  private val JWT_SECRET = "some.jwt.secret"
  private val JWT_EXPIRATION_MS = 86400000

  def generateToken(authentication: String): String = {
    Jwts.builder
      .setSubject(authentication)
      .setIssuedAt(new Date)
      .setExpiration(new Date(System.currentTimeMillis + JwtService.JWT_EXPIRATION_MS))
      .signWith(SignatureAlgorithm.HS512, JwtService.JWT_SECRET)
      .compact
  }

  @throws[AuthenticationException]
  def validateTokenAndGetSubject(authToken: String): String = {
    try {
      Jwts.parser
        .setSigningKey(JwtService.JWT_SECRET)
        .parseClaimsJws(authToken)
        .getBody
        .getSubject
    } catch {
      case throwable: Throwable =>  {
        JwtService.LOGGER.error("JWT exception: " + throwable)
        throw new AuthenticationException(throwable.getMessage)
      }
    }
  }

  @throws[AuthenticationException]
  def getUsernameFromRequest(headers: Headers): Option[String] = {
    headers.get("Authorization") map { auth =>
      (auth.trim.nonEmpty && auth.startsWith("Bearer ")) match {
        case true => auth.substring(7)
      }
    } match {
      case Some(token) => Option(validateTokenAndGetSubject(token))
      case None => Option.empty
    }
  }
}
