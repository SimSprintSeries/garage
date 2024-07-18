package com.sss.garage.dto.league;

import com.sss.garage.dto.game.GameDTO;
import org.springframework.web.multipart.MultipartFile;

public class LeagueDTO {
    private Long id;
    private String name;
    private String platform;
    private GameDTO game;
    public String startDate;
    public Integer eventCount;
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
