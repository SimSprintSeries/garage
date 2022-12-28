package com.sss.garage.facade.auth;

import com.sss.garage.data.auth.JwtTokenData;

public interface AuthenticationFacade {
    JwtTokenData authenticate(final String username, final String password);
}
