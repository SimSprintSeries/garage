package com.sss.garage.model.split;

import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResult;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String split;


    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "split")
    private Set<RaceResult> raceResults;


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

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }
    public void setRaceResults(final Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }
}
