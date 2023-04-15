package com.sss.garage.service.league.impl;

import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.model.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssLeagueService implements LeagueService {

    private LeagueRepository leagueRepository;

    @Override
    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public Optional<League> getLeague(final Long id) {
        return leagueRepository.findById(id);
    }

    @Override
    public void createLeague(final League league) {
        leagueRepository.save(league);
    }

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }
}
