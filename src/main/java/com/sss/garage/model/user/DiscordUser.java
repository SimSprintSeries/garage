package com.sss.garage.model.user;

<<<<<<< HEAD
import jakarta.persistence.*;

import com.sss.garage.model.driver.Driver;
=======
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
>>>>>>> b961c35322d3f07b592c80666471583e0859ff9c

@Entity
public class DiscordUser {

    @Id
    private Long id;

    private String username;

    private String email;

<<<<<<< HEAD
    @OneToOne
    private Driver driver;

    public String getId() {
=======
    private String discriminator;

    @Column(length = 2048)
    private String currentJwtToken;

    private Date tokenExpiryDate;

    public Long getId() {
>>>>>>> b961c35322d3f07b592c80666471583e0859ff9c
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

<<<<<<< HEAD
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

=======
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

    @Override
    public String toString() {
        return id.toString();
    }
>>>>>>> b961c35322d3f07b592c80666471583e0859ff9c
}