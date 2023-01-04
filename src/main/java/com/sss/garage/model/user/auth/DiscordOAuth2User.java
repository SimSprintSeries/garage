package com.sss.garage.model.user.auth;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.sss.garage.model.role.DiscordRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class DiscordOAuth2User implements OAuth2User {

    private String id;
    private String username;
    private Set<DiscordRole> roles;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getName() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Set<DiscordRole> getRoles() {
        return roles;
    }

    public void setRoles(final Set<DiscordRole> roles) {
        this.roles = roles;
    }

    public void setAttributes(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
