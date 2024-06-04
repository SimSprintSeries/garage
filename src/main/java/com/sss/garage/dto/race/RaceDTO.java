package com.sss.garage.dto.race;

import com.sss.garage.dto.presence.PresenceDTO;
import com.sss.garage.dto.track.TrackDTO;
import com.sss.garage.dto.event.EventDTO;
import com.sss.garage.dto.split.SplitDTO;

import java.util.Set;

public class RaceDTO extends EventDTO {
    private SplitDTO split;
    private Boolean activeForPresence;
    private Set<PresenceDTO> presences;


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
}
