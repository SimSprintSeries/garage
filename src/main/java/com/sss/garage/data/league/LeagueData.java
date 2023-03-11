package com.sss.garage.data.league;

import com.sss.garage.data.game.GameData;

public class LeagueData {
    private Long id;
    private String name;
    private String platform;
    private GameData game;

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
}
