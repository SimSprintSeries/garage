package com.sss.garage.model.event;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;

import org.jetbrains.annotations.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Date startDate;

    @NotNull
    @ManyToOne
    private League league;

    private String sprite;

    @OneToMany(mappedBy = "event")
    private Set<Race> races;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(final League league) {
        this.league = league;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(final String sprite) {
        this.sprite = sprite;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(final Set<Race> races) {
        this.races = races;
    }
}
