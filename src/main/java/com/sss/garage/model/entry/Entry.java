package com.sss.garage.model.entry;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.team.Team;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Driver driver;

    @ManyToMany(mappedBy = "entries")
    private Set<Team> teams;

    private String preferredPartner;

    @ManyToOne
    private Game game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public String getPreferredPartner() {
        return preferredPartner;
    }

    public void setPreferredPartner(String preferredPartner) {
        this.preferredPartner = preferredPartner;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
