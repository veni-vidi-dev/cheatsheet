/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.bean.CustomUserDetails;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        Hibernate.initialize(user.get().getRoles());
        return new CustomUserDetails(user.get());
    }
}
