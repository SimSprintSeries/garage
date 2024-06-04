package com.sss.garage.data.race;

import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.split.SplitData;

import java.util.Set;

public class RaceData extends EventData {
    private SplitData split;
    private String displayText;
    private Boolean activeForPresence;
    private Set<PresenceData> presences;

    public SplitData getSplit() {
        return split;
    }

    public void setSplit(final SplitData split) {
        this.split = split;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(final Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }

    public Set<PresenceData> getPresences() {
        return presences;
    }

    public void setPresences(final Set<PresenceData> presences) {
        this.presences = presences;
    }
}
