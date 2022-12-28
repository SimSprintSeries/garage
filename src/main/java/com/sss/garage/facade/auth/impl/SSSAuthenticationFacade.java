package com.sss.garage.facade.auth.impl;

import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.facade.auth.AuthenticationFacade;
import com.sss.garage.service.auth.jwt.impl.SSSJwtTokenService;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //TODO: Could create a similar, @Facade annotation
public class SSSAuthenticationFacade implements AuthenticationFacade {

    private AuthenticationManager authenticationManager;
    private SSSJwtTokenService jwtTokenService;
    private UserService userService;
    private ConversionService conversionService;

    @Override
    public JwtTokenData authenticate(final String username, final String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // throws if anything bad happens
        // user authenticated

        return userService.findUserByUsername(username)
                .map(u -> jwtTokenService.generateForUser(u))
                .map(t -> conversionService.convert(t, JwtTokenData.class))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found")); // Never should happen
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
    public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenService(final SSSJwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }
}
