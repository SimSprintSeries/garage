package com.sss.garage.service.team.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.team.Team;
import com.sss.garage.model.team.TeamRepository;
import com.sss.garage.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SssTeamService implements TeamService {
    private TeamRepository teamRepository;

    private EventRepository eventRepository;

    private RaceResultRepository raceResultRepository;

    @Override
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    /**
     * TODO: Probably needs change of logic
     * @param driver
     * @param league
     * @return
     */
    @Override
    public Optional<Team> findTeamForDriverAndLeague(final Driver driver, final League league) {
        Event event = eventRepository.findFirstByLeagueOrderByStartDateAsc(league); // first event for checking if league has double-raced events

        if (event.getRaces().stream().findFirst().orElseThrow().getName().equals("Parent race")) {
            return Optional.ofNullable(raceResultRepository.findLastTeamByDriverAndLeagueForParentRaces(driver, league));
        }

        return Optional.ofNullable(raceResultRepository.findLastTeamByDriverAndLeague(driver, league));
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Autowired
    public void setEventService(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
