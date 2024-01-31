package com.sss.garage.service.entry;

import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EntryService {
    Page<Entry> getAllEntriesByGame(final Game game, final Pageable pageable);

    Optional<Entry> getEntryById(final Long id);

    void createEntry(final Entry entry);

    void deleteEntry(final Long id);
}
