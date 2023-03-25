package com.sss.garage.facade.league;

import com.sss.garage.data.league.LeagueData;

import java.util.List;

public interface LeagueFacade {
    LeagueData getLeague(final Long id);
    List<LeagueData> getAllLeagues();
}
