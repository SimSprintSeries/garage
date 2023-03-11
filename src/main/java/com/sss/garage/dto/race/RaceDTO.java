package com.sss.garage.dto.race;

import com.sss.garage.dto.event.EventDTO;
import com.sss.garage.dto.split.SplitDTO;

public class RaceDTO extends EventDTO {
    private SplitDTO split;

    public SplitDTO getSplit() {
        return split;
    }

    public void setSplit(final SplitDTO split) {
        this.split = split;
    }
}
