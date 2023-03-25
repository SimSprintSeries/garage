package com.sss.garage.facade.driver.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.facade.driver.DriverFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssDriverFacade implements DriverFacade {
    private DriverService driverService;
    private ConversionService conversionService;

    @Override
    public List<DriverData> getAllDrivers() {
        return driverService.getAllDrivers().stream()
                .map(d -> conversionService.convert(d, DriverData.class))
                .toList();
    }

    @Override
    public Driver getDriver(final Long id) {
        return driverService.getDriver(id).orElseThrow();
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
