package com.sss.garage.data.race;

import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.split.SplitData;

import java.util.Date;
import java.util.Set;

public class RaceData {
    private Long id;
    private Date startDate;
    private SplitData split;
    private String displayText;
    private Boolean activeForPresence;
    private Set<PresenceData> presences;
    private String name;

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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
