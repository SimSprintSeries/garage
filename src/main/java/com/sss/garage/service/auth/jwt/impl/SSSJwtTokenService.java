package com.sss.garage.service.auth.jwt.impl;

import java.util.Date;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sss.garage.model.user.User;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SSSJwtTokenService implements JwtTokenService {

    @Value("${sss.jwt.secret}")
    private String secret;

    @Value("${sss.jwt.token.life.length.millis}")
    private long expirationTime;

    private UserService userService;

    @Override
    public String generateForUser(final User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    @Override
    public Optional<User> extractUserFromToken(String token) {
        String username = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        return userService.findUserByUsername(username);
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
