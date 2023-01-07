package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.League;

@Entity
public class Game {

    @Id
    private Long id;
    private String name;

    private Set<League> leagueList;


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
        this.discordUser = leagueList;
    }
}
