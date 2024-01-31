package com.sss.garage.model.game;

import com.sss.garage.model.entry.Entry;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.league.League;
import com.sss.garage.model.elo.Elo;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "game")
    private Set<League> leagues;

    @OneToMany(mappedBy = "game")
    private Set<Elo> elos;

    @ManyToOne
    private GameFamily gameFamily;

    @OneToMany(mappedBy = "game")
    private Set<Entry> entries;


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

    public GameFamily getGameFamily() {
        return gameFamily;
    }

    public void setGameFamily(final GameFamily gameFamily) {
        this.gameFamily = gameFamily;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }
}
