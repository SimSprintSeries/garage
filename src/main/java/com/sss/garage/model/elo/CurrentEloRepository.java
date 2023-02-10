package com.sss.garage.model.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to retrieve only entities of implicit type Elo
 */
@Repository
public interface CurrentEloRepository extends JpaRepository<Elo, Long> {
    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo AND e.game=:game AND e.driver=:driver")
    Optional<Elo> findByGameAndDriver(Game game, Driver driver);

    @Query("SELECT e FROM Elo e WHERE TYPE(e)=EloHistory AND e.game=:game AND e.driver=:driver")
    Optional<Elo> findHistoryByGameAndDriver(Game game, Driver driver, Sort startDate);

    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo AND e.game=:game")
    Optional<Elo> findByGame(Game game, Sort startDate);
}

