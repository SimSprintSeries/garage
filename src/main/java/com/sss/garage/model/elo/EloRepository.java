package com.sss.garage.model.elo;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.family.GameFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EloRepository extends JpaRepository<Elo, Long> {
    Optional<Elo> findAllByGame(Game game);
    Optional<Elo> findAllByGameFamily(GameFamily gameFamily);
}
