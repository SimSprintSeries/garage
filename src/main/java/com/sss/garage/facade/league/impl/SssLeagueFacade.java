package com.sss.garage.facade.league.impl;

import java.util.List;

import com.sss.garage.data.league.DetailedLeagueData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.league.LeagueFacade;
import com.sss.garage.service.league.LeagueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssLeagueFacade extends SssBaseFacade implements LeagueFacade  {

    private LeagueService leagueService;

    @Override
    public LeagueData getLeague(final Long id) {
        return leagueService.getLeague(id)
                .map(l -> conversionService.convert(l, DetailedLeagueData.class))
                .orElseThrow();
    }

    @Override
    public List<LeagueData> getAllLeagues() {
        return leagueService.findAll().stream().map(l -> conversionService.convert(l, LeagueData.class)).toList();
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }
}
