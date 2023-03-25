package com.sss.garage.service.driver;

import java.util.List;
import java.util.Optional;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.model.driver.Driver;

public interface DriverService {
    Optional<Driver> getDriver(final Long id);

    List<Driver> getAllDrivers();
}
