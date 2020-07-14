/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import bean.{LoginRequestBean, LoginResponseBean}
import javax.inject.{Inject, Singleton}
import javax.naming.AuthenticationException
import repository.UserRepository

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class SecurityService @Inject()(
                   private val userRepository: UserRepository
                 )(implicit ec: ExecutionContext) {

  @throws[AuthenticationException]
  def doLogin(user: LoginRequestBean): Future[Option[LoginResponseBean]] = {
    userRepository.findByUsername(user.username) map { optUser =>
      optUser map { userFromDB =>
        (userFromDB.password.equals(user.password)) match {
          case true => LoginResponseBean(userFromDB, JwtService.generateToken(user.username))
          case false => throw new AuthenticationException("Username or Password is incorrect")
        }
      }
    }
  }

}
