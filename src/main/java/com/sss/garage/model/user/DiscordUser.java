package com.sss.garage.model.user;

import jakarta.persistence.*;

import com.sss.garage.model.driver.Driver;

@Entity
public class DiscordUser {

    @Id
    private String id;

    private String username;

    private String email;

    @OneToOne
    private Driver driver;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

}