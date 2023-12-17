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

    public SimpleDriverDTO getDriverDTO() {
        return driverDTO;
    }

    public void setDriverDTO(SimpleDriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    public Set<TeamDTO> getTeamDTOS() {
        return teamDTOS;
    }

    public void setTeamDTOS(Set<TeamDTO> teamDTOS) {
        this.teamDTOS = teamDTOS;
    }

    public String getPreferredPartner() {
        return preferredPartner;
    }

    public void setPreferredPartner(String preferredPartner) {
        this.preferredPartner = preferredPartner;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }
}
