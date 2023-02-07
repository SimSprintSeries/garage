package com.sss.garage.model.elo;

import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EloRepository extends JpaRepository<Elo, Long> {
    Optional<Elo> findByGameAndDriver(Game game, Driver driver);
}
