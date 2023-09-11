package com.sss.garage.model.league;

import com.sss.garage.model.split.Split;
import jakarta.persistence.*;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.game.Game;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Set;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String platform;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Game game;

    @OneToMany(mappedBy="league")
    private Set<Event> events;

    @OneToMany(mappedBy="league")
    private Set<Split> splits;

    private Boolean active = false;

    private String discordGroupId;

    public Date startDate;

    public Integer raceCount;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(final Set<Event> events) {
        this.events = events;
    }

    public Set<Split> getSplits() {
        return splits;
    }

    public void setSplits(final Set<Split> splitList) {
        this.splits = splits;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public String getDiscordGroupId() {
        return discordGroupId;
    }

    public void setDiscordGroupId(final String discordGroupId) {
        this.discordGroupId = discordGroupId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getRaceCount() {
        return raceCount;
    }

    public void setRaceCount(Integer raceCount) {
        this.raceCount = raceCount;
    }
}
