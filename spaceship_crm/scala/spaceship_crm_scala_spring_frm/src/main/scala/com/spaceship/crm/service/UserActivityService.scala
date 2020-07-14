package com.spaceship.crm.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.spaceship.crm.bean.UserActivityBean
import com.spaceship.crm.repository.{ActivityRepository, UserActivityRepository, UserRepository}

@Service
class UserActivityService(
                           @Autowired private val userActivityRepository: UserActivityRepository,
                           @Autowired private val userRepository: UserRepository,
                           @Autowired private val activityRepository: ActivityRepository
                         ) {

  def getAll: Seq[UserActivityBean] = {
    userActivityRepository.findAll
  }


  def addUserToActivityAndReturnAll(activityId: Long, userId: Long): Seq[UserActivityBean] = {
    userActivityRepository.insert(activityId, userId)
    getAll
  }

}
