package com.sss.garage.model.entry;

import com.sss.garage.model.game.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    Page<Entry> findAllByGame(final Game game, final Pageable pageable);
}
