package com.sss.garage.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DiscordUser {

    @Id
    private Long id;

    private String username;

    private String email;

    private String discriminator;

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
}