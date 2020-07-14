/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository;

import com.spaceship.crm.entity.UserActivity;
import com.spaceship.crm.entity.UserActivityId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends CrudRepository<UserActivity, UserActivityId> {
}
