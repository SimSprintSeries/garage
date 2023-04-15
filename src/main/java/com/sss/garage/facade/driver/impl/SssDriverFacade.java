package com.sss.garage.facade.driver.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.driver.DriverFacade;
import com.sss.garage.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssDriverFacade extends SssBaseFacade implements DriverFacade {
    private DriverService driverService;

    @Override
    public List<DriverData> getAllDrivers() {
        return driverService.getAllDrivers().stream()
                .map(d -> conversionService.convert(d, DriverData.class))
                .toList();
    }

    @Override
    public DriverData getDriver(final Long id) {
        return conversionService.convert(driverService.getDriver(id).orElseThrow(), DriverData.class);
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }
}
