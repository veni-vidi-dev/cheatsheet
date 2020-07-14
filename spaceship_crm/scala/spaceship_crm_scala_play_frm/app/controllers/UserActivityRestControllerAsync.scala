/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package controllers

import bean.LoginRequestBean
import javax.inject.{Inject, Singleton}
import javax.naming.AuthenticationException
import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import play.api.mvc._
import repository.{ActivityRepository, UserRepository}
import security.SecurityService
import service.{UserActivityService, UserService}

import scala.concurrent.duration._
import scala.concurrent.{Await, Awaitable, ExecutionContext}

object UserActivityRestController {
  val LOGGER = LoggerFactory.getLogger(getClass)
}

@Singleton
class UserActivityRestController @Inject() (
                                  private val securityService: SecurityService,
                                  private val userActivityService: UserActivityService,
                                  private val activityRepository: ActivityRepository,
                                  private val userRepository: UserRepository,
                                  val controllerComponents: ControllerComponents
                                )(implicit ec: ExecutionContext)
extends BaseController {

  def doLogin: Action[AnyContent] = Action.async { implicit request =>
    securityService.doLogin(request.body.asJson.get.as[LoginRequestBean]).map {
      case Some(response) => Ok(Json.toJson(response))
      case None => Unauthorized("{\"status\":401}")
    }.recover{
      case ex: Throwable => {
        UserActivityRestController.LOGGER.error(ex.getMessage, ex)
        InternalServerError("{\"status\":500}")
      }
    }
  }

  def getUserActivities: Action[AnyContent] = Action.async {
    userActivityService.getAll map { activities =>
      Ok(Json.toJson(activities))
    }
  }

  def addUserActivity(activityId: Long, userId: Long): Action[AnyContent] = Action.async { implicit request =>
    userActivityService.addUserToActivityAndReturnAll(activityId, userId) map { activities =>
      Ok(Json.toJson(activities))
    }
  }

  def allUsers(): Action[AnyContent] = Action async {
    userRepository.findAll() map { users =>
      Ok(Json.toJson(users))
    }
  }

  def allActivities(): Action[AnyContent] = Action async {
    activityRepository.findAll() map { activities =>
      Ok(Json.toJson(activities))
    }
  }
}
