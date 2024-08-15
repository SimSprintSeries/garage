package com.sss.garage.service.league.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.model.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SssLeagueService implements LeagueService {

    private LeagueRepository leagueRepository;

    @Override
    public Optional<League> getLeague(final Long id) {
        League league = leagueRepository.findById(id).orElseThrow();
        league.setEvents(league.getEvents().stream()
                .sorted(Comparator.comparing(Event::getStartDate)).collect(Collectors.toCollection(LinkedHashSet::new)));
        return Optional.of(league);
    }

    @Override
    public void createLeague(final League league) {
        leagueRepository.save(league);
    }

    @Override
    public void deleteLeague(final Long id) {
        leagueRepository.deleteById(id);
    }

    @Override
    public Page<League> getLeaguesPaginated(final String platform, final String name, final Boolean active, final Pageable pageable) {
        return leagueRepository.findAllByParams(platform, name, active, pageable);
    }

    @Override
    public Page<League> getLeaguesForDriver(final Driver driver, final Pageable pageable) {
        return leagueRepository.findLeaguesForDriver(driver, pageable);
    }

    @Override
    public League getLeagueByName(final String name) {
        return leagueRepository.findByName(name);
    }

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }
}
