package com.sss.garage.service.race.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.service.game.GameService;
import com.sss.garage.service.race.RaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SssRaceService implements RaceService {

    private RaceRepository raceRepository;
    private GameService gameService;

    private final static Sort SORT_BY_START_DATE_ASC = Sort.by(Sort.Direction.ASC, "startDate");

    @Override
    public Optional<Race> findById(final Long id) {
        return raceRepository.findById(id);
    }

    @Override
    public List<Race> getAllScoringRacesSorted() {
        return raceRepository.findAllByPointScoring(true, SORT_BY_START_DATE_ASC);
    }

    @Override
    public List<Race> getGameSortedRacesSince(final Race race) {
        return raceRepository.findAllByStartDateGreaterThanEqual(race.getStartDate(), SORT_BY_START_DATE_ASC).stream()
                .filter(r -> gameService.getGame(race).equals(gameService.getGame(r)))
                .toList();
    }

    @Override
    public List<Race> getAllScoringRacesNotIncludedInEloSorted() {
        return raceRepository.findAllByPointScoringAndIncludedInElo(true, false, SORT_BY_START_DATE_ASC);
    }

    @Override
    public void save(final Race race) {
        raceRepository.save(race);
    }

    @Override
    public void saveAll(final Collection<Race> races) {
        raceRepository.saveAll(races);
    }

    @Override
    public Boolean isQuali(final Race race) {
        return race.getName().equals("Kwalifikacje");
    }

    @Override
    public Page<Race> getAllPlayableRaces(final League league, final Pageable pageable) {
        return raceRepository.findAllByDatePlaceholderAndLeague(true, league, pageable);
    }

    @Override
    public Page<Race> getCompletedPlayableRaces(final League league, final Pageable pageable) {
        return raceRepository.findAllByDatePlaceholderAndStartDateGreaterThanAndLeague(true, new Date(System.currentTimeMillis()), league, pageable);
    }

    @Override
    public Page<Race> getUncompletedPlayableRaces(final League league, final Pageable pageable) {
        return raceRepository.findAllByDatePlaceholderAndStartDateLessThanEqualAndLeague(true, new Date(System.currentTimeMillis()), league, pageable);
    }

    @Override
    public void deleteRace(final Long id) {
        raceRepository.deleteById(id);
    }

    @Override
    public Page<Race> getAllRacesByEvent(final Event event, final Pageable pageable) {
        return raceRepository.findAllByEvent(event, pageable);
    }

    @Autowired
    public void setRaceRepository(final RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }
}
