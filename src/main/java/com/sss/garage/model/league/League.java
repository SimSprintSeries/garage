package com.sss.garage.model.league;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.game.Game;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class League {

    @Id
    private Long id;
    private String split;
    private String platform;

    @ManyToOne
    private Game game;

    @OneToMany(mappedBy="league")
    private Set<Event> eventList;



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

    public Set<Event> getEventList() {
        return eventList;
    }
    public void setEventList(final Set<Event> eventList) {
        this.eventList = eventList;
    }
}
