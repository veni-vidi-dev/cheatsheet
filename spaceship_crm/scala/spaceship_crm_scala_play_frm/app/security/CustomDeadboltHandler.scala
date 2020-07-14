/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package security

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import javax.inject.{Inject, Singleton}
import javax.naming.AuthenticationException
import play.api.mvc.{Request, Result, Results}
import service.UserService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CustomDeadboltHandler @Inject()
(
   private val userService: UserService,
)(implicit ec: ExecutionContext) extends DeadboltHandler {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = Future {None}

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = Future {None}

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] =
    JwtService.getUsernameFromRequest(request.headers) match {
      case Some(username) => {
        userService.loadUserByUsername(username) map {
          case Some(user) => Option(UserDetails(user))
        }
      }
      case None => throw new AuthenticationException("No user")
    }

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    Future(Results.Unauthorized("{\"status\":401}"))
  }
}
