/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import com.spaceship.crm.bean.UserLoginBean
import com.spaceship.crm.repository.ActivityRepository
import com.spaceship.crm.service.SecurityService
import com.spaceship.crm.service.UserActivityService
import com.spaceship.crm.repository.UserRepository
import org.springframework.ui.set
import com.spaceship.crm.bean.NewUserActivityBean

@Controller
@RequestMapping(path = ["/"])
@CrossOrigin
class UserActivityController @Autowired constructor(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val activityRepository: ActivityRepository,
        @Autowired private val securityService: SecurityService,
        @Autowired private val userActivityService: UserActivityService
) {

    @GetMapping("/activities")
    fun getUserActivity(model: Model): String {
        model.addAttribute("activities", userActivityService.getAll())
        return "activities"
    }

    @GetMapping("/addusertoactivity")
    fun addUserToActivity(model: Model): String {
        model.addAttribute("activities", activityRepository.findAll())
        model.addAttribute("users", userRepository.findAll())

        return "addusertoactivity"
    }

    @RequestMapping(value = ["/add"], method = [RequestMethod.POST])
    fun submit(@ModelAttribute("userActivity") userActivity: NewUserActivityBean,
               result: BindingResult, model: ModelMap): String {
        if (result.hasErrors()) {
            return "error"
        }
        val activities = userActivityService.addUserToActivityAndReturnAll(userActivity)
        model.addAttribute("activities", activities)
        return "activities"
    }

    @GetMapping("/login")
    fun login(model: Model): String {
        model["user"] = UserLoginBean()
        return "start"
    }

    @PostMapping("/login")
    fun doLogin(@ModelAttribute("user") user: UserLoginBean,
                result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            return "error"
        }
        securityService.doLogin(UserLoginBean(user.username,user.password))

        return getUserActivity(model)
    }

    @GetMapping("/logout")
    fun logout(model: Model): String {
        securityService.doLogout()
        return login(model)
    }

    @GetMapping("/error")
    fun error(model: Model): String {
        return "error"
    }

}