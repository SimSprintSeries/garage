package com.sss.garage.service.league.impl;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.league.League;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.service.league.LeagueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssLeagueService implements LeagueService {

    private LeagueRepository leagueRepository;

    @Override
    public Optional<League> getLeague(final Long id) {
        return leagueRepository.findById(id);
    }

    @Override
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }
}
