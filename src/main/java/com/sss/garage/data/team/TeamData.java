package com.sss.garage.data.team;

import com.sss.garage.data.entry.EntryData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.raceresult.RaceResultData;

import java.util.Set;

public class TeamData {
    private Long id;

    private String name;

    private GameData gameData;

    private Set<EntryData> entryData;

    private Set<RaceResultData> raceResultData;

    private String colour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameData getGame() {
        return gameData;
    }

    public void setGame(GameData gameData) {
        this.gameData = gameData;
    }

    public Set<EntryData> getEntry() {
        return entryData;
    }

    public void setEntry(Set<EntryData> entryData) {
        this.entryData = entryData;
    }

    public Set<RaceResultData> getRaceResult() {
        return raceResultData;
    }

    public void setRaceResult(Set<RaceResultData> raceResultData) {
        this.raceResultData = raceResultData;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
