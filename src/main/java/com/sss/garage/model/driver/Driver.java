package com.sss.garage.model.driver;

import jakarta.persistence.*;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.raceresult.RaceResult;

import java.util.Set;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "driver")
    private DiscordUser discordUser;

    @OneToMany(mappedBy = "driver")
    private Set<Elo> elo;

    @OneToMany(mappedBy = "driver")
    private Set<RaceResult> raceResults;


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

    public Set<Elo> getElo() {
        return elo;
    }
    public void setElo(Set<Elo> elo) {
        this.elo = elo;
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }
    public void setRaceResults(final Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }
}
