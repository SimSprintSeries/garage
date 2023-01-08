package com.sss.garage.model.driver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.raceresult.RaceResult;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Set;

@Entity
public class Driver {

    @Id
    private Long id;
    private String name;

    @OneToOne(mappedBy = "driver")
    private DiscordUser discordUser;

    @OneToOne(mappedBy = "driver")
    private Elo elo;

    @OneToMany(mappedBy = "driver")
    private Set<RaceResult> raceResultList;


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

    public Elo getElo() {
        return elo;
    }
    public void setElo(Elo elo) {
        this.elo = elo;
    }

    public Set<RaceResult> getRaceResultList() {
        return raceResultList;
    }
    public void setRaceResultList(final Set<RaceResult> raceResultList) {
        this.raceResultList = raceResultList;
    }
}
