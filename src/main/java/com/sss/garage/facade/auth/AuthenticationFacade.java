package com.sss.garage.facade.auth;

import com.sss.garage.data.auth.JwtTokenData;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    JwtTokenData getJwtTokenForCurrentUser();
    void revokeToken(final String token);
}
