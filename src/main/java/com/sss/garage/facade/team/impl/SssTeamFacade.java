package com.sss.garage.facade.team.impl;

import com.sss.garage.data.team.TeamData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.team.TeamFacade;
import com.sss.garage.model.team.Team;
import com.sss.garage.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssTeamFacade extends SssBaseFacade implements TeamFacade {
    private TeamService teamService;

    @Override
    public Page<TeamData> getAllTeams(final Pageable pageable) {
        List<TeamData> teamData = teamService.getAllTeams(pageable).stream()
                .map(t -> conversionService.convert(t, TeamData.class)).toList();

        return new PageImpl<>(teamData);
    }

    @Override
    public TeamData getTeamById(final Long id) {
        return conversionService.convert(teamService.getTeamById(id).orElseThrow(), TeamData.class);
    }

    @Override
    public void createTeam(final TeamData teamData) {
        teamService.createTeam(conversionService.convert(teamData, Team.class));
    }

    @Override
    public void deleteTeam(final Long id) {
        teamService.deleteTeam(id);
    }

    @Autowired
    public void setTeamService(final TeamService teamService) {
        this.teamService = teamService;
    }
}
