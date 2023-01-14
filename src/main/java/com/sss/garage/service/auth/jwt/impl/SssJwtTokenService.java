package com.sss.garage.service.auth.jwt.impl;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.jwt.StringJwtAuthenticationToken;
import com.sss.garage.service.auth.role.RoleService;
import com.sss.garage.service.auth.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SssJwtTokenService implements JwtTokenService {
    private static final Logger logger = LoggerFactory.getLogger(SssJwtTokenService.class);

    @Value("${sss.jwt.secret}")
    private String secret;

    @Value("${sss.jwt.token.life.length.millis}")
    private long expirationTime;

    private UserService userService;
    private RoleService roleService;
    private ConversionService conversionService;
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtTokenData generateForPrincipal(final Authentication principal) {
        final Date expiresAt = new Date(System.currentTimeMillis() + expirationTime);
        final String token = JWT.create()
                .withSubject(principal.getName())
                .withExpiresAt(expiresAt)
                .withArrayClaim("roles", principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(Algorithm.HMAC512(secret.getBytes()));

        final JwtTokenData jwtTokenData = conversionService.convert(token, JwtTokenData.class);
        jwtTokenData.setExpiresAt(expiresAt);
        return jwtTokenData;
    }

    @Override
    public Optional<Authentication> extractAuthenticationFromToken(final String token) {
        if(token.isBlank()){
            return Optional.empty();
        }
        DecodedJWT jwt = decode(token);

        DiscordUser principal = userService.findUserById(jwt.getSubject()).orElseThrow(() -> new UsernameNotFoundException(jwt.getSubject()));
        if(isExpired(jwt)) {
            // Not current token of user or expired
            userService.revokeUserToken(principal);
            return Optional.empty();
        }
        if(!isTokenOfUser(token, principal)) {
            logger.warn("Old token used for user: " + principal);
            return Optional.empty();
        }

        Set<DiscordRole> roles = jwt.getClaim("roles").asList(String.class).stream()
                .map(r -> roleService.findById(r).orElseThrow(() -> new EntityNotFoundException("TODO: In future in case the role does not exist on our database, fetch it again from discord server")))
                .collect(Collectors.toSet());

        return Optional.of(new StringJwtAuthenticationToken(principal, roles));
//        return userService.findUserByUsername(username);
    }

    @Override
    public Boolean isExpired(final String token) {
        return isExpired(decode(token));
    }

    @Override
    public Boolean isTokenOfUser(final String token, final DiscordUser discordUser) {
        return token != null && passwordEncoder.matches(token, discordUser.getCurrentJwtToken());
    }

    private Boolean isExpired(final DecodedJWT jwt) {
        return new Date(System.currentTimeMillis()).after(jwt.getExpiresAt());
    }

    private DecodedJWT decode(final String token) {
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token);
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(final RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
