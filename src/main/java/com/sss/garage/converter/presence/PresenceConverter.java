package com.sss.garage.converter.presence;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresenceConverter extends BaseConverter implements Converter<Presence, PresenceData> {
    private DriverService driverService;

    @Override
    public PresenceData convert(final Presence source) {
        final PresenceData data = new PresenceData();

        data.setIsPresent(source.getIsPresent());
        data.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));

        List<Driver> driversInSplit = driverService.getDriversByLeague(source.getEvent().getLeague(), PageRequest.of(0,50))
                .stream().toList();
        if(driversInSplit.contains(source.getDriver())) {
            data.setIsAssignedToSplit(true);
        } else if(!driversInSplit.contains(source.getDriver()) && source.getIsPresent()) {
            data.setIsAssignedToSplit(false);
        }


        return data;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }
}
