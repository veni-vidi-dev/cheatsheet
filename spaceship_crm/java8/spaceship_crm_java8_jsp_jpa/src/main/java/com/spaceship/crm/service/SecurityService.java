/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.bean.CustomUserDetails;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.model.UserLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User doLogin(UserLoginModel user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            return ((CustomUserDetails)auth.getPrincipal()).getUser();
        }
        return null;
    }

    public void doLogout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
