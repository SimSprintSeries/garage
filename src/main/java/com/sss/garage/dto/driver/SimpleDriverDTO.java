package com.sss.garage.dto.driver;

import com.sss.garage.dto.team.TeamDTO;
import com.sss.garage.dto.user.BasicDiscordUserDTO;

public class SimpleDriverDTO {
    private Long id;
    private String nickname;

    private String firstName;

    private String lastName;

    private String shortName;

    private TeamDTO team;

    private BasicDiscordUserDTO discordUser;

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

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public BasicDiscordUserDTO getDiscordUser() {
        return discordUser;
    }

    public void setDiscordUser(final BasicDiscordUserDTO discordUser) {
        this.discordUser = discordUser;
    }
}
