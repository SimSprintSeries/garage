package com.sss.garage.model.event;

import jakarta.persistence.*;

import com.sss.garage.model.league.League;
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
    @ManyToOne
    private League league;

    private String sprite;

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
}
