package com.sss.garage.data.driver;

import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.data.user.user.DiscordUserData;

import java.util.Set;

public class DriverData {
    private Long id;
    private String nickname;
    private Set<EloData> elos;
    private Integer totalWins;
    private Integer totalTopTenResults;
    private Integer totalRacesDriven;
    private Set<Integer> stats;
    private Set<SplitData> splits;
    private TeamData team;

    private Integer podiums;

    private Integer polePositions;

    private Integer fastestLaps;

    private DiscordUserData discordUser;

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

    public Set<EloData> getElos() {
        return elos;
    }

    public void setElos(final Set<EloData> elos) {
        this.elos = elos;
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

    public TeamData getTeam() {
        return team;
    }

    public void setTeam(TeamData team) {
        this.team = team;
    }

    public Integer getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(final Integer totalWins) {
        this.totalWins = totalWins;
    }

    public Integer getTotalTopTenResults() {
        return totalTopTenResults;
    }

    public void setTotalTopTenResults(final Integer totalTopTenResults) {
        this.totalTopTenResults = totalTopTenResults;
    }

    public Integer getTotalRacesDriven() {
        return totalRacesDriven;
    }

    public void setTotalRacesDriven(final Integer totalRacesDriven) {
        this.totalRacesDriven = totalRacesDriven;
    }

    public Integer getPodiums() {
        return podiums;
    }

    public void setPodiums(final Integer podiums) {
        this.podiums = podiums;
    }

    public Integer getPolePositions() {
        return polePositions;
    }

    public void setPolePositions(final Integer polePositions) {
        this.polePositions = polePositions;
    }

    public Integer getFastestLaps() {
        return fastestLaps;
    }

    public void setFastestLaps(final Integer fastestLaps) {
        this.fastestLaps = fastestLaps;
    }

    public DiscordUserData getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(final DiscordUserData discordUser) {
        this.discordUser = discordUser;
    }
}
