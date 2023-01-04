package com.sss.garage.service.auth.jwt;

import java.util.Optional;

import com.sss.garage.model.user.DiscordUser;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {
    String generateForPrincipal(final Authentication principal);
    Optional<Authentication> extractAuthenticationFromToken(String token);
}
