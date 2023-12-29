package com.sss.garage.dto.entry;

import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.dto.game.GameDTO;
import com.sss.garage.dto.team.TeamDTO;

import java.util.Set;

public class EntryDTO {
    private Long id;

    private SimpleDriverDTO driverDTO;

    private Set<TeamDTO> teamDTOS;

    private String preferredPartner;

    private GameDTO gameDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleDriverDTO getDriver() {
        return driverDTO;
    }

    public void setDriver(SimpleDriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    public Set<TeamDTO> getTeam() {
        return teamDTOS;
    }

    public void setTeam(Set<TeamDTO> teamDTOS) {
        this.teamDTOS = teamDTOS;
    }

    public String getPreferredPartner() {
        return preferredPartner;
    }

    public void setPreferredPartner(String preferredPartner) {
        this.preferredPartner = preferredPartner;
    }

    public GameDTO getGame() {
        return gameDTO;
    }

    public void setGame(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }
}
