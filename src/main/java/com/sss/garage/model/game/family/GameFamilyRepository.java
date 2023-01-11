package com.sss.garage.model.game.family;

import com.sss.garage.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameFamilyRepository extends JpaRepository<GameFamily, Integer> {
    Optional<Game> findByName(String name);
}
