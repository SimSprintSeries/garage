package com.sss.garage.facade.league;

import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.league.League;

import java.util.List;

public interface LeagueFacade {
    League getLeague(final Long id);

    List<LeagueData> getAllLeagues();
}
