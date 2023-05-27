package com.sss.garage.facade.driver;

import com.sss.garage.data.driver.DriverData;

import java.util.List;

public interface DriverFacade {
    List<DriverData> getAllDrivers();

    DriverData getDriver(final Long id);

    void createDriver(final DriverData driverData);

    void deleteDriver(final Long id);
}
