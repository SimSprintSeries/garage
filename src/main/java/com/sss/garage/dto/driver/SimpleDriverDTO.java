package com.sss.garage.dto.driver;

import com.sss.garage.dto.team.TeamDTO;

public class SimpleDriverDTO {
    private Long id;
    private String nickname;

    private TeamDTO team;

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

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}
