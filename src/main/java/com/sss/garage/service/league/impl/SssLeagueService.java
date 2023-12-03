package com.sss.garage.service.league.impl;

import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.model.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SssLeagueService implements LeagueService {

    private LeagueRepository leagueRepository;

    private EventRepository eventRepository;

    @Override
    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public Optional<League> getLeague(final Long id) {
        return leagueRepository.findById(id);
    }

    @Override
    public void createLeague(final League league) {
        setStartDateAndEventCount(league);
        leagueRepository.save(league);
    }

    @Override
    public Page<League> getLeaguesPaginated(final String platform, final String name, final Boolean active, final Pageable pageable) {
        return leagueRepository.findAllByParams(platform, name, active, pageable);
    }

    private void setStartDateAndEventCount(final League league) {
        LocalDate date = eventRepository.findFirstByLeagueOrderByStartDateAsc(league).getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        league.setStartDate(date.format(formatter));
        league.setEventCount(eventRepository.countByLeague(league));
    }

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Autowired
    public void setEventRepository(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
