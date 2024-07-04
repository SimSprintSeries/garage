package com.sss.garage.dto.race;

import com.sss.garage.dto.presence.PresenceDTO;
import com.sss.garage.dto.split.SplitDTO;

import java.util.Set;

public class RaceDTO {
    private SplitDTO split;
    private Boolean activeForPresence;
    private Set<PresenceDTO> presences;
    private Long id;
    private String startDate;
    private String name;

    public SplitDTO getSplit() {
        return split;
    }

    public void setSplit(final SplitDTO split) {
        this.split = split;
    }

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(final Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }

    public Set<PresenceDTO> getPresences() {
        return presences;
    }

    public void setPresences(final Set<PresenceDTO> presences) {
        this.presences = presences;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
