package com.sss.garage.facade.driver.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.driver.DriverFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssDriverFacade extends SssBaseFacade implements DriverFacade {
    private DriverService driverService;

    @Override
    public DriverData getDriver(final Long id) {
        return conversionService.convert(driverService.getDriver(id).orElseThrow(), DriverData.class);
    }

    @Override
    public void createDriver(final DriverData driverData) {
        driverService.createDriver(conversionService.convert(driverData, Driver.class));
    }

    @Override
    public void deleteDriver(final Long id) {
        driverService.deleteDriver(id);
    }

    @Override
    public Page<DriverData> getDriversPaginated(final Pageable pageable) {
        Page<Driver> driver = driverService.getDriversPaginated(pageable);
        return driver.map(d -> conversionService.convert(d, DriverData.class));
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }
}
