package com.sss.garage.facade.driver;

import com.sss.garage.data.driver.DriverData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverFacade {
    List<DriverData> getAllDrivers();

    DriverData getDriver(final Long id);

    void createDriver(final DriverData driverData);

    void deleteDriver(final Long id);

    Page<DriverData> getDriversPaginated(final Pageable pageable);
}
