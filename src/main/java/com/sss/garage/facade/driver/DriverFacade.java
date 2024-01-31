package com.sss.garage.facade.driver;

import com.sss.garage.data.driver.DriverData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverFacade {
    DriverData getDriver(final Long id);

    void createDriver(final DriverData driverData);

    void deleteDriver(final Long id);

    Page<DriverData> getDriversPaginated(final Pageable pageable);

    Page<DriverData> getDriversByLeague(final String leagueId, final Pageable pageable);
}
