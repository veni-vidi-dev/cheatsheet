/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.security;

import com.spaceship.crm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<String> jwt = getJwtFromRequest(request);
            if (jwt.isPresent()) {
                String user = jwtService.validateTokenAndGetId(jwt.get());
                UserDetails userDetails = userService.loadUserByUsername(user);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (AuthenticationException ex) {
            response.setHeader("Content-Type", "application/json");
            response.setStatus(401);
            response.getOutputStream().write("{\"error\":\"401\"}".getBytes());
            return;
        } catch (Exception ex) {
            LOGGER.error("Could not set user authentication in security context", ex);
            response.setHeader("Content-Type", "application/json");
            response.setStatus(500);
            response.getOutputStream().write("{\"error\":\"500\",\"description\":\"User authentication failed\"}".getBytes());
            return;
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return Optional.ofNullable(bearerToken.substring(7));
        }
        return Optional.empty();
    }
}
