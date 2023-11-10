package com.sss.garage.service.stats.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.stats.Stats;
import com.sss.garage.model.stats.StatsRepository;
import com.sss.garage.service.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssStatsService implements StatsService {
    private RaceResultRepository raceResultRepository;

    /*@Override
    public Page<Stats> getStatsPaginated(final Driver driver, final Game game, final Race race, final Pageable pageable) {

        return getStats(driver, game, race);
    }*/

    @Override
    public Stats getStats(final Driver driver, final League league) {
        return setStats(driver, league);
    }

    private Stats setStats(final Driver driver, final League league) {
        Stats stats = new Stats();
        stats.setWins(raceResultRepository.countFinishPositionByParams(driver, league, 1, 1));
        stats.setPodiums(raceResultRepository.countFinishPositionByParams(driver, league, 1, 3));
        stats.setTop10(raceResultRepository.countFinishPositionByParams(driver, league, 1, 10));
        stats.setDnfs(raceResultRepository.countDnfByParams(driver, league));
        stats.setDsqs(raceResultRepository.countDsqByParams(driver, league));
        stats.setFastestLaps(raceResultRepository.countFlByParams(driver, league));
        return stats;
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }
}
