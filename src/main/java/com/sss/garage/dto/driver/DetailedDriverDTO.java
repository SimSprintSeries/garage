package com.sss.garage.dto.driver;

import com.sss.garage.model.elo.Elo;

import java.util.Set;

public class DetailedDriverDTO extends SimpleDriverDTO {
    private String discordName;
    private Set<Elo> elos;

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(final String discordName) {
        this.discordName = discordName;
    }

    public Set<Elo> getElos() {
        return elos;
    }

    public void setElos(final Set<Elo> elos) {
        this.elos = elos;
    }
}
