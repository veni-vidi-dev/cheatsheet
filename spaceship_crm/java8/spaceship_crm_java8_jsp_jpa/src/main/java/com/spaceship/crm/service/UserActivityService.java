/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.model.NewUserActivityModel;
import com.spaceship.crm.bean.UserActivityBean;
import com.spaceship.crm.entity.UserActivity;
import com.spaceship.crm.repository.ActivityRepository;
import com.spaceship.crm.repository.UserActivityRepository;
import com.spaceship.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(userActivityRepository.findAll().spliterator(), false)
                .map(userActivity -> toUserActivityBean(userActivity))
                .collect(Collectors.toList());

    }

    public List<UserActivityBean> addUserToActivityAndReturnAll(NewUserActivityModel bean) {
        UserActivity userActivity = new UserActivity();
        userActivity.setActivity(activityRepository.findById(bean.getActivityId()).get());
        userActivity.setUser(userRepository.findById(bean.getUserId()).get());
        userActivityRepository.save(userActivity);
        return getAll();
    }

    public UserActivityBean toUserActivityBean(UserActivity userActivity) {
        UserActivityBean uab = new UserActivityBean();
        uab.setUserId(userActivity.getUser().getId());
        uab.setFirstName(userActivity.getUser().getFirstName());
        uab.setLastName(userActivity.getUser().getLastName());

        uab.setActivityName(userActivity.getActivity().getName());

        uab.setStart(userActivity.getActivity().getStartDate());
        uab.setEnd(userActivity.getActivity().getEndDate());
        uab.setAdded(userActivity.getCreatedAt());
        return uab;
    }

}
