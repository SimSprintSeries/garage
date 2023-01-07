package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.user.DiscordUser;

@Entity
public class Driver {

    @Id
    private Long id;
    private String name;

    private DiscordUser discordUser;


    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public DiscordUser getDiscordUser() {
        return discordUser;
    }
    public void setDiscordUser(DiscordUser discordUser) {
        this.discordUser = discordUser;
    }
}
