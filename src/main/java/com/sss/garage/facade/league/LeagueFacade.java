package com.sss.garage.facade.league;

import com.sss.garage.data.league.LeagueData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LeagueFacade {
    LeagueData getLeague(final Long id);

    void createLeague(final LeagueData leagueData) throws IOException;

    void deleteLeague(final Long id);

    Page<LeagueData> getLeaguesPaginated(final String platform, final String name, final Boolean active, final Pageable pageable);

    Page<LeagueData> getLeaguesForDriver(final String driverId, final Pageable pageable);

    LeagueData getLeagueByName(final String name);
}
