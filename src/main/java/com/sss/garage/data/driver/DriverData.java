package com.sss.garage.data.driver;

import com.sss.garage.model.elo.Elo;

import java.util.Set;

public class DriverData {
    private Long id;
    private String nickname;
    private Set<Elo> elos;
    private String discordName;
    private Set<Integer> stats;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public Set<Elo> getElos() {
        return elos;
    }

    public void setElos(final Set<Elo> elos) {
        this.elos = elos;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(final String discordName) {
        this.discordName = discordName;
    }

    public Set<Integer> getStats() {
        return stats;
    }

    public void setStats(final Set<Integer> stats) {
        this.stats = stats;
    }
}
