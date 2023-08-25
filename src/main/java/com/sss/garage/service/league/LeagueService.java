package com.sss.garage.service.league;

import com.sss.garage.model.league.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LeagueService {
    Optional<League> getLeague(final Long id);

    List<League> getAllLeagues();

    void createLeague(final League league);

    Page<League> getLeaguesPaginated(final String platform, final String name, final Boolean active, final Pageable pageable);
}
