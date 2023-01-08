package com.sss.garage.model.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.league.League;
import com.sss.garage.model.elo.Elo;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Game {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "game")
    private Set<League> leagueList;

    @OneToMany(mappedBy = "game")
    private Set<Elo> eloList;


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

    public Set<League> getLeagueList() {
        return leagueList;
    }
    public void setLeagueList(Set<League> leagueList) {
        this.leagueList = leagueList;
    }

    public Set<Elo> getEloList() {
        return eloList;
    }
    public void setEloList(Set<Elo> eloList) {
        this.eloList = eloList;
    }
}
