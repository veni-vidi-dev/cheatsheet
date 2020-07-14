/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.controller;

import com.spaceship.crm.bean.LoginResponseBean;
import com.spaceship.crm.bean.UserActivityBean;
import com.spaceship.crm.entity.Activity;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.service.ActivityService;
import com.spaceship.crm.service.SecurityService;
import com.spaceship.crm.service.UserActivityService;
import com.spaceship.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rest")
@CrossOrigin
public class UserActivityRestController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBean> doLogin(@RequestBody User user) {
        Optional<LoginResponseBean> loggedInUser = securityService.doLogin(user);
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/activities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserActivityBean> getUserActivities() {
        return userActivityService.getAll();
    }

    @PostMapping(path = "/activity/{activityId}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserActivityBean> addUserActivity(@PathVariable Long activityId, @PathVariable Long userId) {
        return userActivityService.addUserToActivityAndReturnAll(activityId, userId);
    }

    @GetMapping(path = "/allactivities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Activity> getAllActivities() {
        return activityService.getAll();
    }

    @GetMapping(path = "/allusers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAll();
    }
    
}
