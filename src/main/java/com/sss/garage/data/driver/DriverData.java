package com.sss.garage.data.driver;

import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.data.user.user.DiscordUserData;

import java.util.Set;

public class DriverData {
    private Long id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String shortName;
    private Set<EloData> elos;
    private Integer totalWins;
    private Integer totalTopTenResults;
    private Integer totalRacesDriven;
    private Set<Integer> stats;
    private Set<LeagueData> leagues;
    private TeamData team;

    private Integer podiums;

    private Integer polePositions;

    private Integer fastestLaps;

    private DiscordUserData discordUser;

    private Integer penaltyPointsF1;

    private Integer penaltyPointsAC;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
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

    public Set<LeagueData> getLeagues() {
        return leagues;
    }

    public void setLeagues(final Set<LeagueData> leagues) {
        this.leagues = leagues;
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

    public Integer getPenaltyPointsF1() {
        return penaltyPointsF1;
    }

    public void setPenaltyPointsF1(final Integer penaltyPointsF1) {
        this.penaltyPointsF1 = penaltyPointsF1;
    }

    public Integer getPenaltyPointsAC() {
        return penaltyPointsAC;
    }

    public void setPenaltyPointsAC(final Integer penaltyPointsAC) {
        this.penaltyPointsAC = penaltyPointsAC;
    }
}
