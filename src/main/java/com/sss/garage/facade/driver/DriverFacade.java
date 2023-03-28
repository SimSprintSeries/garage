package com.sss.garage.facade.driver;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.model.driver.Driver;

import java.util.List;

public interface DriverFacade {
    List<DriverData> getAllDrivers();

    DriverData getDriver(final Long id);
}
