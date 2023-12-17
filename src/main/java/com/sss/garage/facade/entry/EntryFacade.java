package com.sss.garage.facade.entry;

import com.sss.garage.data.entry.EntryData;
import com.sss.garage.data.game.GameData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntryFacade {
    Page<EntryData> getAllEntriesByGame(final String gameId, final Pageable pageable);

    EntryData getEntryById(final Long id);

    void createEntry(final EntryData entryData);

    void deleteEntry(final Long id);
}
