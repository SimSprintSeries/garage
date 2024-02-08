package com.sss.garage.model.driver;

import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.report.Report;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.team.Team;
import jakarta.persistence.*;

import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.raceresult.RaceResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @OneToMany(mappedBy = "reportingDriver")
    private List<Report> reporting;

    @OneToMany(mappedBy = "reportedDriver")
    private List<Report> reported;

    @OneToMany(mappedBy = "driver")
    private List<Entry> entries;

    @OneToMany(mappedBy = "driver")
    private List<Classification> classifications;

    /**
     * Null if not calculated yet!
     * Remember to add in calculation in results processing!
     */
    @Nullable
    private Integer totalWins;

    /**
     * Null if not calculated yet!
     * Remember to add in calculation in results processing!
     */
    @Nullable
    private Integer totalTopTenResults;

    /**
     * Null if not calculated yet!
     * Remember to add in calculation in results processing!
     */
    @Nullable
    private Integer totalRacesDriven;

    @Nullable
    private Integer podiums;

    @Nullable
    private Integer polePositions;

    @Nullable
    private Integer fastestLaps;

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

    public List<Report> getReporting() {
        return reporting;
    }

    public void setReporting(List<Report> reporting) {
        this.reporting = reporting;
    }

    public List<Report> getReported() {
        return reported;
    }

    public void setReported(List<Report> reported) {
        this.reported = reported;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public Integer getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(final Integer totalWins) {
        this.totalWins = totalWins;
    }

    @Nullable
    public Integer getTotalTopTenResults() {
        return totalTopTenResults;
    }

    public void setTotalTopTenResults(@Nullable final Integer totalTopTenResults) {
        this.totalTopTenResults = totalTopTenResults;
    }

    @Nullable
    public Integer getTotalRacesDriven() {
        return totalRacesDriven;
    }

    public void setTotalRacesDriven(@Nullable final Integer totalRacesDriven) {
        this.totalRacesDriven = totalRacesDriven;
    }

    @Nullable
    public Integer getPodiums() {
        return podiums;
    }

    public void setPodiums(@Nullable final Integer podiums) {
        this.podiums = podiums;
    }

    @Nullable
    public Integer getPolePositions() {
        return polePositions;
    }

    public void setPolePositions(@Nullable final Integer polePositions) {
        this.polePositions = polePositions;
    }

    @Nullable
    public Integer getFastestLaps() {
        return fastestLaps;
    }

    public void setFastestLaps(@Nullable final Integer fastestLaps) {
        this.fastestLaps = fastestLaps;
    }
}
