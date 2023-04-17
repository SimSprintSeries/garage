package com.sss.garage.service.league;

import com.sss.garage.model.league.League;

import java.util.List;
import java.util.Optional;

public interface LeagueService {
    Optional<League> getLeague(final Long id);

    List<League> getAllLeagues();

    void createLeague(final League league);
}
