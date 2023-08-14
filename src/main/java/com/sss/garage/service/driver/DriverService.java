package com.sss.garage.service.driver;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    Optional<Driver> getDriver(final Long id);

    List<Driver> getAllDrivers();

    void createDriver(final Driver driver);

    void deleteDriver(final Long id);

    Page<Driver> getDriversPaginated(final Pageable pageable);
}
