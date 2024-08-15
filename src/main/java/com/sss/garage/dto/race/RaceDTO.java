package com.sss.garage.dto.race;

import com.sss.garage.dto.split.SplitDTO;

public class RaceDTO {
    private SplitDTO split;
    private Long id;
    private String name;
    private String displayText;

    public SplitDTO getSplit() {
        return split;
    }

    public void setSplit(final SplitDTO split) {
        this.split = split;
    }

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

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }
}
