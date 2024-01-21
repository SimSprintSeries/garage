package com.sss.garage.service.stats.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.stats.Stats;
import com.sss.garage.service.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssStatsService implements StatsService {
    private RaceResultRepository raceResultRepository;

    @Override
    public Stats getStats(final Driver driver, final League league, final Game game, final String split) {
        return setStats(driver, league, game, split);
    }

    private Stats setStats(final Driver driver, final League league, final Game game, final String split) {
        Stats stats = new Stats();
        stats.setStarts(raceResultRepository.countFinishPositionByParams(driver, league, game, split, 1, 50));
        stats.setWins(raceResultRepository.countFinishPositionByParams(driver, league, game, split, 1, 1));
        stats.setPodiums(raceResultRepository.countFinishPositionByParams(driver, league, game, split, 1, 3));
        stats.setTop10(raceResultRepository.countFinishPositionByParams(driver, league, game, split, 1, 10));
        stats.setDnfs(raceResultRepository.countDnfByParams(driver, league, game, split));
        stats.setDsqs(raceResultRepository.countDsqByParams(driver, league, game, split));
        stats.setFastestLaps(raceResultRepository.countFlByParams(driver, league, game, split));
        return stats;
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }
}
