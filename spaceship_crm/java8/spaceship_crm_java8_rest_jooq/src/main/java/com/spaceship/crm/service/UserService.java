/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.bean.CustomUserDetails;
import com.spaceship.crm.bean.UserBean;
import com.spaceship.crm.entity.Role;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.jooq.tables.records.RoleRecord;
import com.spaceship.crm.jooq.tables.records.UsersRecord;
import com.spaceship.crm.repository.RoleRepository;
import com.spaceship.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(ur ->
                        new User(ur.getId(), ur.getUsername(), null, ur.getFirstName(), ur.getLastName())
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UsersRecord> optionalUser = userRepository.findByUsername(s);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        UsersRecord user = optionalUser.get();
        List<RoleRecord> roles = roleRepository.findAllByUserId(user.getId());
        UserBean userBean = new UserBean(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                null,
                roles.stream().map(
                        roleRecord -> new Role(roleRecord.getId(), roleRecord.getName())
                ).collect(Collectors.toList()),
                null
        );
        return new CustomUserDetails(userBean);
    }
}
