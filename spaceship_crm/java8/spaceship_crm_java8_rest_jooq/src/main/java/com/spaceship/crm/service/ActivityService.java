/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.entity.Activity;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.repository.ActivityRepository;
import com.spaceship.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAll() {
        return activityRepository.findAll()
                .stream()
                .map(a ->
                        new Activity(a.getId(), a.getName(), a.getStartDate(), a.getEndDate())
                )
                .collect(Collectors.toList());
    }
}
