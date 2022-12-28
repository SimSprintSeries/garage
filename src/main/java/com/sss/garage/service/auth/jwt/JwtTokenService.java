package com.sss.garage.service.auth.jwt;

import java.util.Optional;

import com.sss.garage.model.user.User;

public interface JwtTokenService {
    String generateForUser(final User user);
    Optional<User> extractUserFromToken(String token);
}
