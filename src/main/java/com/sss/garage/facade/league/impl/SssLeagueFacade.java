package com.sss.garage.facade.league.impl;

import com.sss.garage.data.league.LeagueData;
import com.sss.garage.facade.league.LeagueFacade;
import com.sss.garage.model.league.League;
import com.sss.garage.service.league.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssLeagueFacade implements LeagueFacade {

    private LeagueService leagueService;
    private ConversionService conversionService;

    @Override
    public List<LeagueData> getAllLeagues() {
        return leagueService.getAllLeagues().stream()
                .map(l -> conversionService.convert(l, LeagueData.class))
                .toList();
    }

    @Override
    public League getLeague(final Long id) {
        return leagueService.getLeague(id).orElseThrow();
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
