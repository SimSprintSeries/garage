package com.sss.garage.facade.entry.impl;

import com.sss.garage.data.entry.EntryData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.entry.EntryFacade;
import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.game.Game;
import com.sss.garage.service.entry.EntryService;
import com.sss.garage.service.game.GameService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssEntryFacade extends SssBaseFacade implements EntryFacade {
    private EntryService entryService;

    private GameService gameService;

    @Override
    public Page<EntryData> getAllEntriesByGame(final String gameId, final Pageable pageable) {
        Game game = null;

        if(Strings.isNotEmpty(gameId)) {
            game = gameService.getGame(Long.valueOf(gameId)).orElseThrow();
        }

        List<EntryData> entryData =
                entryService.getAllEntriesByGame(game, pageable)
                        .stream().map(e -> conversionService.convert(e, EntryData.class)).toList();

        return new PageImpl<>(entryData);
    }

    @Override
    public EntryData getEntryById(final Long id) {
        return conversionService.convert(entryService.getEntryById(id).orElseThrow(), EntryData.class);
    }

    @Override
    public void createEntry(final EntryData entryData) {
        entryService.createEntry(conversionService.convert(entryData, Entry.class));
    }

    @Override
    public void deleteEntry(final Long id) {
        entryService.deleteEntry(id);
    }

    @Autowired
    public void setEntryService(final EntryService entryService) {
        this.entryService = entryService;
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }
}
