package com.sss.garage.service.entry.impl;

import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.entry.EntryRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.service.entry.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SssEntryService implements EntryService {
    private EntryRepository entryRepository;

    @Override
    public Page<Entry> getAllEntriesByGame(final Game game, final Pageable pageable) {
        return entryRepository.findAllByGame(game, pageable);
    }

    @Override
    public Optional<Entry> getEntryById(final Long id) {
        return entryRepository.findById(id);
    }

    @Override
    public void createEntry(Entry entry) {
        entryRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

    @Autowired
    public void setEntryRepository(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }
}
