package com.sss.garage.data.league;

import com.sss.garage.data.game.GameData;

import java.util.Date;

public class LeagueData {
    private Long id;
    private String name;
    private String displayText;
    private String platform;
    private GameData game;
    private Date startDate;
    private Integer raceCount;

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

    public GameData getGame() {
        return game;
    }

    public void setGame(final GameData game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
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
