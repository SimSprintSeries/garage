package com.sss.garage.service.elo.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.elo.history.EloHistoryRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.service.elo.EloHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SssEloHistoryService implements EloHistoryService {

    private EloHistoryRepository eloHistoryRepository;

    @Override
    public Optional<EloHistory> findLatestEloHistory(final Game game, final Driver driver) {
        return eloHistoryRepository.findFirstByDriverAndGameOrderByValidUntilDesc(driver, game);
    }

    public List<EloHistory> getEloHistories(final Game game, final Driver driver) {
        return eloHistoryRepository.findAllByGameAndDriver(game, driver, Sort.by(Sort.Direction.ASC, "startDate"));
    }

    @Override
    public void deleteAllHistoryAfterIncluding(final EloHistory eloHistory) {
        Set<EloHistory> forRemoval = eloHistory.getDriver().getEloHistories().stream()
                .filter(eh -> eh.getGame().equals(eloHistory.getGame()))
                .filter(eh -> eh.getValidUntil().after(eloHistory.getValidUntil()) || eh.getValidUntil().equals(eloHistory.getValidUntil()))
                .collect(Collectors.toSet());

        eloHistory.getDriver().getEloHistories().removeAll(forRemoval);
        eloHistory.getRace().getEloHistories().removeAll(forRemoval);

        eloHistoryRepository.deleteAll(forRemoval);
    }

    @Autowired
    public void setEloHistoryRepository(final EloHistoryRepository eloHistoryRepository) {
        this.eloHistoryRepository = eloHistoryRepository;
    }
}
