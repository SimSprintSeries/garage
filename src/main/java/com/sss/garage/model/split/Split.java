package com.sss.garage.model.split;

import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.user.DiscordUser;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Split {

    @Id
    private Long id;
    private String split;


    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "driver")
    private Set<RaceResult> raceResultList;


    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return split;
    }
    public void setName(final String split) {
        this.split = split;
    }

    public League getLeague() {
        return league;
    }
    public void setLeague(League league) {
        this.league = league;
    }

    public Set<RaceResult> getRaceResultList() {
        return raceResultList;
    }
    public void setRaceResultList(final Set<RaceResult> raceResultList) {
        this.raceResultList = raceResultList;
    }
}
