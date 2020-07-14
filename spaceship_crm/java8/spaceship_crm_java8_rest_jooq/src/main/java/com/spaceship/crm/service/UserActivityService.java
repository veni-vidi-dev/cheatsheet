/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.bean.UserActivityBean;
import com.spaceship.crm.repository.ActivityRepository;
import com.spaceship.crm.repository.UserActivityRepository;
import com.spaceship.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public List<UserActivityBean> getAll() {
        return userActivityRepository.findAll();

    }

    public List<UserActivityBean> addUserToActivityAndReturnAll(Long activityId, Long userId) {
        userActivityRepository.insert(activityId, userId);
        return getAll();
    }

}
