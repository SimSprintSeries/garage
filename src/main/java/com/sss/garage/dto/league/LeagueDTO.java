package com.sss.garage.dto.league;

import com.sss.garage.dto.game.GameDTO;

public class LeagueDTO {
    private Long id;
    private String name;
    private String platform;
    private GameDTO game;
    public String startDate;
    public Integer eventCount;
    private String banner;
    private String logo;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
