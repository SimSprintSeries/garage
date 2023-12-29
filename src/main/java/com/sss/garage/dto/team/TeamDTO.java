package com.sss.garage.dto.team;

import com.sss.garage.dto.entry.EntryDTO;
import com.sss.garage.dto.game.GameDTO;

import java.util.Set;

public class TeamDTO {
    private Long id;

    private String name;

    private GameDTO gameDTO;

    private Set<EntryDTO> entryDTOS;

    private String colour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameDTO getGame() {
        return gameDTO;
    }

    public void setGame(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    public Set<EntryDTO> getEntry() {
        return entryDTOS;
    }

    public void setEntry(Set<EntryDTO> entryDTOS) {
        this.entryDTOS = entryDTOS;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
