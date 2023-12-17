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

    public DriverData getDriverData() {
        return driverData;
    }

    public void setDriverData(DriverData driverData) {
        this.driverData = driverData;
    }

    public Set<TeamData> getTeamData() {
        return teamData;
    }

    public void setTeamData(Set<TeamData> teamData) {
        this.teamData = teamData;
    }

    public String getPreferredPartner() {
        return preferredPartner;
    }

    public void setPreferredPartner(String preferredPartner) {
        this.preferredPartner = preferredPartner;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
}
