package com.sss.garage.service.auth.jwt.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.jwt.StringJwtAuthenticationToken;
import com.sss.garage.service.auth.role.RoleService;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SssJwtTokenService implements JwtTokenService {
    @Value("${sss.jwt.secret}")
    private String secret;

    @Value("${sss.jwt.token.life.length.millis}")
    private long expirationTime;

    private UserService userService;
    private RoleService roleService;

    @Override
    public String generateForPrincipal(final Authentication principal) {
        return JWT.create()
                .withSubject(principal.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withArrayClaim("roles", principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    @Override
    public Optional<Authentication> extractAuthenticationFromToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token);

        if(new Date(System.currentTimeMillis()).after(jwt.getExpiresAt())){
            // token expired
            return Optional.empty();
        }

        DiscordUser principal = userService.findUserById(jwt.getSubject()).orElseThrow();
        Set<DiscordRole> roles = jwt.getClaim("roles").asList(String.class).stream()
                .map(r -> roleService.findById(r).orElseThrow(() -> new EntityNotFoundException("TODO: In future in case the role does not exist on our database, fetch it again from discord server")))
                .collect(Collectors.toSet());

        return Optional.of(new StringJwtAuthenticationToken(principal, roles));
//        return userService.findUserByUsername(username);
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(final RoleService roleService) {
        this.roleService = roleService;
    }
}
