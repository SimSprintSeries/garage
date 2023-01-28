package com.sss.garage.service.auth.jwt;

import java.util.Collection;
import java.util.Set;

import com.sss.garage.model.user.DiscordUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class StringJwtAuthenticationToken implements Authentication {
    private boolean authenticated;
    private final Collection<? extends GrantedAuthority> authorities;
    private final DiscordUser principal;

    public StringJwtAuthenticationToken(final DiscordUser principal, Set<? extends GrantedAuthority> authorities) {
        this.principal = principal;
        this.authorities = authorities;
        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return principal.getId().toString();
    }
}
