package com.sss.garage.service.game.impl;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.event.EventService;
import com.sss.garage.service.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssGameService implements GameService {

    private EventService eventService;

    @Override
    public Game getGame(final Race race) {
        return eventService.getEvent(race).getLeague().getGame();
    }

    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }
}
