/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String JWT_SECRET = "some.jwt.secret";
    private static final long JWT_EXPIRATION_MS = 86400000;

    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }


    public String validateTokenAndGetId(String authToken) throws AuthenticationException {
        try {
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken).getBody().getSubject();
        } catch (Exception ex) {
            throw new AuthenticationServiceException(ex.getMessage());
        }
    }
}
