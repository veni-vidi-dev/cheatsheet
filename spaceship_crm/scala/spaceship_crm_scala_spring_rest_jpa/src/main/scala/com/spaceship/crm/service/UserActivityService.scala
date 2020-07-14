/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service

import java.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.spaceship.crm.bean.UserActivityBean
import com.spaceship.crm.entity.UserActivity
import com.spaceship.crm.repository.{ActivityRepository, UserActivityRepository, UserRepository}

import scala.collection.JavaConverters._

@Service
@Transactional
class UserActivityService(
                           @Autowired private val userActivityRepository: UserActivityRepository,
                           @Autowired private val userRepository: UserRepository,
                           @Autowired private val activityRepository: ActivityRepository
                         ) {

  def getAll: util.List[UserActivityBean] = {
    userActivityRepository.findAll
      .asScala
      .map(userActivity => toUserActivityBean(userActivity))
      .toList
      .asJava
  }


  def addUserToActivityAndReturnAll(activityId: Long, userId: Long): util.List[UserActivityBean] = {
    val userActivity: UserActivity = new UserActivity
    userActivity.activity = activityRepository.findById(activityId).get
    userActivity.user = userRepository.findById(userId).get
    userActivityRepository.save(userActivity)
    getAll
  }

  def toUserActivityBean(userActivity: UserActivity): UserActivityBean = {
    new UserActivityBean(
      userActivity.user.id,
      userActivity.user.firstName,
      userActivity.user.lastName,
      userActivity.activity.name,
      userActivity.activity.startDate,
      userActivity.activity.endDate,
      userActivity.createdAt
    )
  }
}
