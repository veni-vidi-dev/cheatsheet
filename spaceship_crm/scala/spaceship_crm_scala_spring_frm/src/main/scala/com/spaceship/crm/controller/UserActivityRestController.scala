package com.spaceship.crm.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{MediaType, ResponseEntity}
import org.springframework.web.bind.annotation._
import com.spaceship.crm.bean.{LoginResponseBean, UserActivityBean}
import com.spaceship.crm.frm.entity.User
import com.spaceship.crm.security.SecurityService
import com.spaceship.crm.service.UserActivityService

import scala.collection.JavaConverters._

@RestController
@RequestMapping(path = Array("/rest"))
@CrossOrigin
class UserActivityRestController(
                                  @Autowired private val securityService: SecurityService,
                                  @Autowired private val userActivityService: UserActivityService
                                ) {

  @PostMapping(path = Array("/login"))
  def doLogin(@RequestBody user: User): ResponseEntity[LoginResponseBean] = {
    val loggedInUser: LoginResponseBean = securityService.doLogin(user)
    ResponseEntity.ok(loggedInUser)
  }

  @GetMapping(
    path = Array("/activities"),
    produces = Array(MediaType.APPLICATION_JSON_VALUE)
  )
  def getUserActivities: java.util.List[UserActivityBean] = userActivityService.getAll.toList.asJava

  @PostMapping(
    path = Array("/activity/{activityId}/user/{userId}"),
    produces = Array(MediaType.APPLICATION_JSON_VALUE)
  )
  def addUserActivity(@PathVariable activityId: Long, @PathVariable userId: Long): java.util.List[UserActivityBean] = {
    userActivityService.addUserToActivityAndReturnAll(activityId, userId).toList.asJava
  }
}
