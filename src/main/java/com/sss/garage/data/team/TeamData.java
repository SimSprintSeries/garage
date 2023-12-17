package com.sss.garage.data.team;

import com.sss.garage.data.entry.EntryData;
import com.sss.garage.data.game.GameData;

import java.util.Set;

public class TeamData {
    private Long id;

    private String name;

    private GameData gameData;

    private Set<EntryData> entryData;

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

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Set<EntryData> getEntryData() {
        return entryData;
    }

    public void setEntryData(Set<EntryData> entryData) {
        this.entryData = entryData;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
