package com.sss.garage.service.team;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TeamService {
    Page<Team> getAllTeams(final Pageable pageable);

    Optional<Team> getTeamById(final Long id);

    void createTeam(final Team team);

    void deleteTeam(final Long id);

    Optional<Team> findTeamForDriverAndLeague(final Driver driver, final League league);
}
