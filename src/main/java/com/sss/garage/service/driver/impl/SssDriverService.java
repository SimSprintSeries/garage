package com.sss.garage.service.driver.impl;

import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.service.driver.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssDriverService implements DriverService {

    private DriverRepository driverRepository;

    @Override
    public Optional<Driver> getDriver(final Long id) {
        return driverRepository.findById(id);
    }

    @Autowired
    public void setDriverRepository(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
}
