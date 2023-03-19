package com.sss.garage.facade.league;

import java.util.List;

import com.sss.garage.data.league.LeagueData;

public interface LeagueFacade {
    LeagueData getLeague(final Long id);
    List<LeagueData> getAllLeagues();
}
