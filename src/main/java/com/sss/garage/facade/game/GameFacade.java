package com.sss.garage.facade.game;

import java.util.List;

import com.sss.garage.data.game.GameData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameFacade {
    GameData getGame(final Long id);
    void createGame(final GameData gameData);
    void deleteGame(final Long id);
    Page<GameData> getGamesPaginated(final Pageable pageable);
}
