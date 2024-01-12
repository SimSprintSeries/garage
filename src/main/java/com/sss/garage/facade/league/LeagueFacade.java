package com.sss.garage.facade.league;

import com.sss.garage.data.league.LeagueData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LeagueFacade {
    LeagueData getLeague(final Long id);

    void createLeague(final LeagueData leagueData);

    Page<LeagueData> getLeaguesPaginated(final String platform, final String name, final Boolean active, final Pageable pageable);
}
