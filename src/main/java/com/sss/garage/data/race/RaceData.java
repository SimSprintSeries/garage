package com.sss.garage.data.race;

import com.sss.garage.data.event.EventData;
import com.sss.garage.data.split.SplitData;

public class RaceData extends EventData {
    private SplitData split;
    private String displayText;

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
}
