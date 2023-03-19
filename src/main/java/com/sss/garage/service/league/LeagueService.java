package com.sss.garage.service.league;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.league.League;

public interface LeagueService {
    Optional<League> getLeague(final Long id);
    List<League> findAll();
}
