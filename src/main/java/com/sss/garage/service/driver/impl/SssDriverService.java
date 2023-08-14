package com.sss.garage.service.driver.impl;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.service.driver.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssDriverService implements DriverService {

    private DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

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

    @Autowired
    public void setDriverRepository(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
}
