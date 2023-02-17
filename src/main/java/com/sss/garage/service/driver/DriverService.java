package com.sss.garage.service.driver;

import java.util.Optional;

import com.sss.garage.model.driver.Driver;

public interface DriverService {
    Optional<Driver> getDriver(final Long id);
}
