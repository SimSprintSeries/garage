package com.sss.garage.facade.team;

import com.sss.garage.data.team.TeamData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamFacade {
    Page<TeamData> getAllTeams(final Pageable pageable);

    TeamData getTeamById(final Long id);

    void createTeam(final TeamData teamData);

    void deleteTeam(final Long id);
}
