package com.sss.garage.service.auth.jwt;

import java.util.Optional;

import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.model.user.DiscordUser;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {
    JwtTokenData generateForAuthentication(final Authentication authentication);
    Optional<Authentication> extractAuthenticationFromToken(final String token);
    Boolean isExpired(final String token);
    Boolean isTokenOfUser(final String token, final DiscordUser discordUser);
}
