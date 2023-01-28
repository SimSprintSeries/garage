package com.sss.garage.model.user;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DiscordUser {

    @Id
    private Long id;

    private String username;

    private String email;

    private String discriminator;

    private String currentJwtToken;

    private Date tokenExpiryDate;

    private boolean rolesUpToDate = false;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(final String discriminator) {
        this.discriminator = discriminator;
    }

    public String getCurrentJwtToken() {
        return currentJwtToken;
    }

    public void setCurrentJwtToken(final String currentJwtToken) {
        this.currentJwtToken = currentJwtToken;
    }

    public Date getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(final Date tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
    }

    public boolean isRolesUpToDate() {
        return rolesUpToDate;
    }

    public void setRolesUpToDate(final boolean rolesUpToDate) {
        this.rolesUpToDate = rolesUpToDate;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}