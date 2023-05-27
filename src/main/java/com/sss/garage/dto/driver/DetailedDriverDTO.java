package com.sss.garage.dto.driver;

import com.sss.garage.dto.elo.EloDTO;
import com.sss.garage.dto.split.SplitDTO;

import java.util.Set;

public class DetailedDriverDTO extends SimpleDriverDTO {
    private String discordName;
    private Set<EloDTO> elos;

    private Set<SplitDTO> splits;

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(final String discordName) {
        this.discordName = discordName;
    }

    public Set<EloDTO> getElos() {
        return elos;
    }

    public void setElos(final Set<EloDTO> elos) {
        this.elos = elos;
    }

    public Set<SplitDTO> getSplits() {
        return splits;
    }

    public void setSplits(final Set<SplitDTO> splits) {
        this.splits = splits;
    }
}
