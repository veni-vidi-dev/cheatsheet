/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.controller;

import com.spaceship.crm.model.NewUserActivityModel;
import com.spaceship.crm.bean.UserActivityBean;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.model.UserLoginModel;
import com.spaceship.crm.repository.ActivityRepository;
import com.spaceship.crm.repository.UserRepository;
import com.spaceship.crm.service.SecurityService;
import com.spaceship.crm.service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/jsp")
@CrossOrigin
public class UserActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserActivityController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private SecurityService securityService;


    @GetMapping("/activities")
    public String getUserActivity(Model model) {
        model.addAttribute("activities", userActivityService.getAll());
        return "activities";
    }

    @GetMapping("/addusertoactivity")
    public String addUserToActivity(Model model) {
        model.addAttribute("activities", activityRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userActivity", new NewUserActivityModel());

        return "addusertoactivity";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submit(@ModelAttribute("userActivity") NewUserActivityModel userActivity,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        List<UserActivityBean> activities = userActivityService.addUserToActivityAndReturnAll(userActivity);
        model.addAttribute("activities", activities);
        return "activities";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserLoginModel());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("user") UserLoginModel user,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }
        securityService.doLogin(user);

        return getUserActivity(model);
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        securityService.doLogout();
        return login(model);
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }

}
