package com.sss.garage.data.entry;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.team.TeamData;

import java.util.Set;

public class EntryData {
    private Long id;

    private DriverData driverData;

    private Set<TeamData> teamData;

    private String preferredPartner;

    private GameData gameData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriverData getDriver() {
        return driverData;
    }

    public void setDriver(DriverData driverData) {
        this.driverData = driverData;
    }

    public Set<TeamData> getTeam() {
        return teamData;
    }

    public void setTeam(Set<TeamData> teamData) {
        this.teamData = teamData;
    }

    public String getPreferredPartner() {
        return preferredPartner;
    }

    public void setPreferredPartner(String preferredPartner) {
        this.preferredPartner = preferredPartner;
    }

    public GameData getGame() {
        return gameData;
    }

    public void setGame(GameData gameData) {
        this.gameData = gameData;
    }
}
