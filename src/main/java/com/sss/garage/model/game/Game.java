package com.sss.garage.model.game;

import jakarta.persistence.*;

import com.sss.garage.model.league.League;
import com.sss.garage.model.elo.Elo;

import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "game")
    private Set<League> leagues;

    @OneToMany(mappedBy = "game")
    private Set<Elo> elos;


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

    public Set<League> getLeagues() {
        return leagues;
    }
    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }

    public Set<Elo> getElos() {
        return elos;
    }
    public void setElos(Set<Elo> elos) {
        this.elos = elos;
    }
}
