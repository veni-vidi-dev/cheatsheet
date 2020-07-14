/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.service;

import com.spaceship.crm.bean.CustomUserDetails;
import com.spaceship.crm.bean.LoginResponseBean;
import com.spaceship.crm.entity.User;
import com.spaceship.crm.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Optional<LoginResponseBean> doLogin(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );
        Authentication auth = authenticationManager.authenticate(token);
        if (auth.isAuthenticated()) {
            return Optional.of(new LoginResponseBean(
                    ((CustomUserDetails)auth.getPrincipal()).getUser(),
                    jwtService.generateToken(auth)
            ));
        }
        return Optional.empty();
    }

}
