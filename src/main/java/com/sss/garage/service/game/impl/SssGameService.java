package com.sss.garage.service.game.impl;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.event.EventService;
import com.sss.garage.service.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssGameService implements GameService {

    private EventService eventService;
    private GameRepository gameRepository;

    @Override
    public Game getGame(final Race race) {
        return eventService.getEvent(race).getLeague().getGame();
    }

    @Override
    public Optional<Game> getGame(final Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public void createGame(final Game game) {
        gameRepository.save(game);
    }

    @Override
    public void deleteGame(final Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Page<Game> getGamesPaginated(final Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setGameRepository(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
