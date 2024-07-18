package com.sss.garage.data.league;

import com.sss.garage.data.game.GameData;
import org.springframework.web.multipart.MultipartFile;

public class LeagueData {
    private Long id;
    private String name;
    private String displayText;
    private String platform;
    private GameData game;
    private String startDate;
    private Integer eventCount;
    private MultipartFile bannerFile;
    private MultipartFile logoFile;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(final Integer eventCount) {
        this.eventCount = eventCount;
    }

    public MultipartFile getBannerFile() {
        return bannerFile;
    }

    public void setBannerFile(final MultipartFile bannerFile) {
        this.bannerFile = bannerFile;
    }

    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(final MultipartFile logoFile) {
        this.logoFile = logoFile;
    }
}
