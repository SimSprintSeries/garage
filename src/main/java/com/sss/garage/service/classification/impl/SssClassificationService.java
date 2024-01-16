package com.sss.garage.service.classification.impl;

import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.team.Team;
import com.sss.garage.model.team.TeamRepository;
import com.sss.garage.service.classification.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SssClassificationService implements ClassificationService {
    private RaceResultRepository raceResultRepository;

    private EventRepository eventRepository;

    private DriverRepository driverRepository;

    private TeamRepository teamRepository;

    @Override
    public Page<Classification> getClassification(final League league, final Pageable pageable) {
        return setClassification(league, pageable);
    }

    public Page<Classification> getClassificationForTeams(final League league, final Pageable pageable) {
        return setClassificationForTeams(league, pageable);
    }

    private Page<Classification> setClassification(final League league, final Pageable pageable) {
        List<Classification> classifications = new ArrayList<>();
        Event event = eventRepository.findFirstByLeagueOrderByStartDateAsc(league); // first event for checking if league has double-raced events
        for (Driver driver : driverRepository.findDriversByLeague(league)) {
            Classification classification = new Classification();
            classification.setDriver(driver);
            if (event.getRaces().stream().findFirst().orElseThrow().getName().equals("Parent race")) {
                classification.setTeam(raceResultRepository.findLastTeamByDriverAndLeagueForParentRaces(driver, league));
            } else {
                classification.setTeam(raceResultRepository.findLastTeamByDriverAndLeague(driver, league));
            }
            classification.setPoints(raceResultRepository.findPointsByDriverAndLeague(driver, league));
            classification.setP1Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 1));
            classification.setP2Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 2));
            classification.setP3Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 3));
            classification.setP4Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 4));
            classification.setP5Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 5));
            classification.setP6Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 6));
            classification.setP7Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 7));
            classification.setP8Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 8));
            classification.setP9Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 9));
            classification.setP10Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 10));
            classifications.add(classification);
        }
        sortClassification(classifications);
        return new PageImpl<>(classifications, pageable, classifications.size());
    }

    private Page<Classification> setClassificationForTeams(final League league, final Pageable pageable) {
        List<Classification> classifications = new ArrayList<>();
        for (Team team : teamRepository.findTeamsByLeague(league)) {
            Classification classification = new Classification();
            classification.setTeam(team);
            classification.setPoints(raceResultRepository.findPointsByTeamAndLeague(team, league));
            classification.setP1Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 1));
            classification.setP2Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 2));
            classification.setP3Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 3));
            classification.setP4Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 4));
            classification.setP5Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 5));
            classification.setP6Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 6));
            classification.setP7Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 7));
            classification.setP8Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 8));
            classification.setP9Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 9));
            classification.setP10Count(raceResultRepository.countFinishPositionByTeamAndLeague(team, league, 10));
            classifications.add(classification);
        }
        sortClassification(classifications);
        return new PageImpl<>(classifications, pageable, classifications.size());
    }

    private void sortClassification(final List<Classification> classifications) {
        classifications.sort(Comparator.comparing(Classification::getPoints)
                .thenComparing(Classification::getP1Count).thenComparing(Classification::getP2Count)
                .thenComparing(Classification::getP3Count).thenComparing(Classification::getP4Count)
                .thenComparing(Classification::getP5Count).thenComparing(Classification::getP6Count)
                .thenComparing(Classification::getP7Count).thenComparing(Classification::getP8Count)
                .thenComparing(Classification::getP9Count).thenComparing(Classification::getP10Count).reversed());
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
