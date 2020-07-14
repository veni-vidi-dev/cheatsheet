/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.spaceship.crm.bean.NewUserActivityBean
import com.spaceship.crm.bean.UserActivityBean
import com.spaceship.crm.entity.UserActivity
import com.spaceship.crm.repository.ActivityRepository
import com.spaceship.crm.repository.UserActivityRepository
import com.spaceship.crm.repository.UserRepository
import java.util.*


@Service
class UserActivityService(
        @Autowired private val userActivityRepository: UserActivityRepository,
        @Autowired private val userRepository: UserRepository,
        @Autowired private val activityRepository: ActivityRepository
) {
    fun getAll(): List<UserActivityBean> {
        return userActivityRepository.findAll()
                .map { userActivity -> toUserActivityBean(userActivity) }

    }

    fun addUserToActivityAndReturnAll(bean: NewUserActivityBean): List<UserActivityBean> {
        val userActivity = UserActivity(
                userRepository.findById(bean.user!!).get(),
                activityRepository.findById(bean.activity!!).get(),
                Date()
        )
        userActivityRepository.save(userActivity)
        return getAll()
    }

    fun toUserActivityBean(userActivity: UserActivity): UserActivityBean {
        return UserActivityBean(
                userActivity.user.id,
                userActivity.user.firstName,
                userActivity.user.lastName,
                userActivity.activity.id,
                userActivity.activity.name,
                userActivity.activity.startDate,
                userActivity.activity.endDate,
                userActivity.createdAt
        )
    }
}