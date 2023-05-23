package com.sss.garage.data.driver;

import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.split.SplitData;

import java.util.List;
import java.util.Set;

public class DriverData {
    private Long id;
    private String nickname;
    private List<EloData> elos;
    private String discordName;
    private Set<Integer> stats;
    private Set<SplitData> splits;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public List<EloData> getElos() {
        return elos;
    }

    public void setElos(final List<EloData> elos) {
        this.elos = elos;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(final String discordName) {
        this.discordName = discordName;
    }

    public Set<Integer> getStats() {
        return stats;
    }

    public void setStats(final Set<Integer> stats) {
        this.stats = stats;
    }

    public Set<SplitData> getSplits() {
        return splits;
    }

    public void setSplits(final Set<SplitData> splits) {
        this.splits = splits;
    }
}
