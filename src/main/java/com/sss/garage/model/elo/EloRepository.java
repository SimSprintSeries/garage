package com.sss.garage.model.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EloRepository extends JpaRepository<Elo, Long> {
    Optional<Elo> findByGameAndDriver(Game game, Driver driver);
    Optional<List<Elo>> findByGame(Game game);
}
