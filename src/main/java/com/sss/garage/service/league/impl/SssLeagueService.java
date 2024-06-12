package com.sss.garage.service.league.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.model.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SssLeagueService implements LeagueService {

    private LeagueRepository leagueRepository;

    @Override
    public Optional<League> getLeague(final Long id) {
        return leagueRepository.findById(id);
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

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }
}
