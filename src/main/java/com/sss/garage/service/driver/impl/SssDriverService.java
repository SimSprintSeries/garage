package com.sss.garage.service.driver.impl;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.split.Split;
import com.sss.garage.service.driver.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssDriverService implements DriverService {

    private DriverRepository driverRepository;

    @Override
    public Optional<Driver> getDriver(final Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public void createDriver(final Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(final Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Page<Driver> getDriversPaginated(final Pageable pageable) {
        return driverRepository.findAll(pageable);
    }

    @Override
    public Page<Driver> getDriversByLeague(final League league, final Pageable pageable) {
        List<Driver> drivers = driverRepository.findDriversByLeaguesIsContaining(league);
        return new PageImpl<>(drivers, pageable, drivers.size());
    }

    @Override
    public Page<Driver> getDriversByRace(final Race race, final Pageable pageable) {
        List<Driver> drivers = driverRepository.findDriversByRace(race);
        return new PageImpl<>(drivers, pageable, drivers.size());
    }

    @Override
    public void saveDriver(final Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void setDriversForSplit(final List<Driver> drivers) {
        driverRepository.saveAll(drivers);
    }

    @Autowired
    public void setDriverRepository(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
}
