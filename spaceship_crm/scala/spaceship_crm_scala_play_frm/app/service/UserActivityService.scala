/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package service

import bean.UserActivityBean
import javax.inject.{Inject, Singleton}
import repository.{ActivityRepository, UserActivityRepository, UserRepository}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserActivityService @Inject() (
                           private val userActivityRepository: UserActivityRepository,
                           private val userRepository: UserRepository,
                           private val activityRepository: ActivityRepository
                         )(implicit ec: ExecutionContext) {

  def getAll: Future[Seq[UserActivityBean]] = {
    userActivityRepository.findAll
  }


  def addUserToActivityAndReturnAll(activityId: Long, userId: Long): Future[Seq[UserActivityBean]] = {
    userActivityRepository.insert(activityId, userId)
    getAll
  }

}
