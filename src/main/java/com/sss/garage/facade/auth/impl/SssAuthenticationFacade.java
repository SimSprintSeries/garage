package com.sss.garage.facade.auth.impl;

import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.auth.AuthenticationFacade;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.jwt.impl.SssJwtTokenService;
import com.sss.garage.service.auth.user.UserService;
import com.sss.garage.service.session.SessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service //TODO: Could create a similar, @Facade annotation
public class SssAuthenticationFacade extends SssBaseFacade implements AuthenticationFacade {
    Logger logger = LoggerFactory.getLogger(SssAuthenticationFacade.class);

    private JwtTokenService jwtTokenService;
    private UserService userService;
    private SessionService sessionService;
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtTokenData getJwtTokenForCurrentUser() {
        final Authentication principal = sessionService.getCurrentAuthentication();
        JwtTokenData token = jwtTokenService.generateForAuthentication(principal);
        updateUserAttributes(principal, token);
        return token;
    }

    @Override
    public void revokeToken(final String token) {
        DiscordUser currentUser = sessionService.getCurrentUser();
        if(token == null || !jwtTokenService.isTokenOfUser(token, currentUser)) {
            logger.error("Attempt to revoke token of another driver! " +
                    "Revoked token: " + token +
                    ", current driver: " + currentUser);
            throw new AccessDeniedException("You cannot revoke token of another driver!");
        }
        userService.revokeUserToken(currentUser);
    }

    private void updateUserAttributes(final Authentication principal, final JwtTokenData jwtTokenData) {
        final DiscordUser user = conversionService.convert(principal.getPrincipal(), DiscordUser.class);
        user.setCurrentJwtToken(passwordEncoder.encode(jwtTokenData.getToken()));
        user.setTokenExpiryDate(jwtTokenData.getExpiresAt());
        user.setRolesUpToDate(true);
        userService.saveUser(user);
    }

    @Autowired
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setSessionService(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJwtTokenService(final SssJwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }
}
