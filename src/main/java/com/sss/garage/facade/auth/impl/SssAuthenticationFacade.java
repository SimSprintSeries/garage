package com.sss.garage.facade.auth.impl;

import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.facade.auth.AuthenticationFacade;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.jwt.impl.SssJwtTokenService;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service //TODO: Could create a similar, @Facade annotation
public class SssAuthenticationFacade implements AuthenticationFacade {

    private JwtTokenService jwtTokenService;
    private UserService userService;
    private ConversionService conversionService;

    @Override
    public JwtTokenData getJwtToken(final Authentication principal) {
        if(!principal.isAuthenticated()) {
            throw new AccessDeniedException("Principal not authenticated");
        }
        JwtTokenData token = conversionService.convert(jwtTokenService.generateForPrincipal(principal), JwtTokenData.class);
        updateUserAttributes(principal, token);
        return token;
    }

    private void updateUserAttributes(final Authentication principal, final JwtTokenData jwtTokenData) {
        final DiscordUser user = conversionService.convert(principal.getPrincipal(), DiscordUser.class);
        user.setCurrentJwtToken(jwtTokenData.getToken());
        userService.saveUser(user);
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setJwtTokenService(final SssJwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }
}
