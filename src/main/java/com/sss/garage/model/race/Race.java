package com.sss.garage.model.race;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.report.Report;
import com.sss.garage.model.racepointtype.RacePointType;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.split.Split;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Date startDate;

    @ManyToOne
    private Split split;

    @ManyToOne
    private Event event;

    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "race")
    private Set<RaceResult> raceResults;

    @OneToMany(mappedBy = "parentRaceEvent")
    private Set<Race> containedRaces;

    @ManyToOne
    private Race parentRaceEvent;

    /**
     * Elo history with value from BEFORE elo calculation for this race
     */
    @OneToMany(mappedBy = "race", orphanRemoval = true)
    private Set<EloHistory> eloHistories;

    private Boolean includedInElo = false;

    /**
     * Indicates if race is the placeholder for the race as in event for one SplitController.
     * False only when it is a sprint or feature race (part of whole 'weekend' but we play it in one evening)
     */
    private Boolean datePlaceholder = true;

    /**
     * Indicates if this race can score any points to the championship
     */
    private Boolean pointScoring = true;

    @OneToMany(mappedBy = "race")
    private List<Report> reports;

    @Enumerated
    private RacePointType pointType;

    private Boolean activeForPresence = false;

    @OneToMany(mappedBy = "race")
    private Set<Presence> presences;

    public Split getSplit() {
        return split;
    }

    public void setSplit(final Split split) {
        this.split = split;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event) {
        this.event = event;
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(final Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }

    public Set<Race> getContainedRaces() {
        return containedRaces;
    }

    public void setContainedRaces(final Set<Race> containedRaces) {
        this.containedRaces = containedRaces;
    }

    public Race getParentRaceEvent() {
        return parentRaceEvent;
    }

    public void setParentRaceEvent(final Race parentRaceEvent) {
        this.parentRaceEvent = parentRaceEvent;
    }

    public Boolean getIncludedInElo() {
        return includedInElo;
    }

    public void setIncludedInElo(Boolean includedInElo) {
        this.includedInElo = includedInElo;
    }

    public Set<EloHistory> getEloHistories() {
        return eloHistories;
    }

    public void setEloHistories(final Set<EloHistory> eloHistories) {
        this.eloHistories = eloHistories;
    }

    public Boolean getDatePlaceholder() {
        return datePlaceholder;
    }

    public void setDatePlaceholder(final Boolean datePlaceholder) {
        this.datePlaceholder = datePlaceholder;
    }

    public Boolean getPointScoring() {
        return pointScoring;
    }

    public void setPointScoring(final Boolean pointScoring) {
        this.pointScoring = pointScoring;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public RacePointType getPointType() {
        return pointType;
    }

    public void setPointType(final RacePointType racePointType) {
        this.pointType = racePointType;
    }

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }

    public Set<Presence> getPresences() {
        return presences;
    }

    public void setPresences(Set<Presence> presences) {
        this.presences = presences;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull final Date startDate) {
        this.startDate = startDate;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(final League league) {
        this.league = league;
    }
}
