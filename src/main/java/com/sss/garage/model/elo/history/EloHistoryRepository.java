package com.sss.garage.model.elo.history;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EloHistoryRepository extends JpaRepository<EloHistory, Long> {
    List<EloHistory> findAllByGameAndDriver(Game game, Driver driver, Sort sort);
    Optional<EloHistory> findFirstByDriverAndGameOrderByValidUntilDesc(final Driver driver, final Game game);
}
