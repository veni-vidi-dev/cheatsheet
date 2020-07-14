/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository;

import com.spaceship.crm.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
