package com.sss.garage.model.split;

import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Entity
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String split;

    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "split")
    private Set<Race> races;

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public String getSplit() {
        return split;
    }
    public void setSplit(final String split) {
        this.split = split;
    }

    public League getLeague() {
        return league;
    }
    public void setLeague(League league) {
        this.league = league;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(final Set<Race> races) {
        this.races = races;
    }
}
