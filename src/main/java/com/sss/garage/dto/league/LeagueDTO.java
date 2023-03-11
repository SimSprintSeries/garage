package com.sss.garage.dto.league;

import com.sss.garage.dto.game.GameDTO;

public class LeagueDTO {
    private Long id;
    private String name;
    private String platform;
    private GameDTO game;

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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(final GameDTO game) {
        this.game = game;
    }
}
