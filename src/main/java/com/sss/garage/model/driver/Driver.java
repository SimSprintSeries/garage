package com.sss.garage.model.driver;

import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.penalty.Penalty;
import com.sss.garage.model.split.Split;
import jakarta.persistence.*;

import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.raceresult.RaceResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToOne
    private DiscordUser discordUser;

    @OneToMany(mappedBy = "driver", targetEntity = Elo.class)
    private Set<Elo> elos;

    @OneToMany(mappedBy = "driver", orphanRemoval = true)
    private Set<EloHistory> eloHistories;

    @OneToMany(mappedBy = "driver")
    private Set<RaceResult> raceResults;

    @ManyToMany
    private Set<Split> splits;

    @OneToMany(mappedBy = "driver")
    private List<Penalty> penalties;

    @OneToMany(mappedBy = "driver")
    private List<Entry> entries;


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

    public Set<Elo> getElos() {
        return elos;
    }

    public void setElos(Set<Elo> elos) {
        this.elos = elos;
    }

    public Set<EloHistory> getEloHistories() {
        return eloHistories;
    }

    public void setEloHistories(final Set<EloHistory> eloHistories) {
        this.eloHistories = eloHistories;
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(final Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    public Set<Split> getSplits() {
        return splits;
    }

    public void setSplits(final Set<Split> splits) {
        this.splits = splits;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
