package com.sss.garage.service.elo;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.game.Game;

public interface EloHistoryService {
    Optional<EloHistory> findLatestEloHistory(final Game game, final Driver driver);
    List<EloHistory> getEloHistories(final Game game, final Driver driver);
    void deleteAllHistoryAfterIncluding(final EloHistory eloHistory);
}
