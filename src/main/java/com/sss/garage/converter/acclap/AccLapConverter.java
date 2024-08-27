package com.sss.garage.converter.acclap;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.model.acclap.AccLap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccLapConverter extends BaseConverter implements Converter<AccLap, AccLapData> {
    @Override
    public AccLapData convert(final AccLap source) {
        final AccLapData data = new AccLapData();

        data.setSector1(source.getSector1());
        data.setSector2(source.getSector2());
        data.setSector3(source.getSector3());
        data.setLaptime(source.getLaptime());
        data.setFirstName(source.getFirstName());
        data.setLastName(source.getLastName());
        data.setShortName(source.getShortName());
        data.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));
        data.setCarName(source.getCarName());
        data.setValidLaps(source.getValidLaps());
        data.setTheoreticalBest(source.getTheoreticalBest());
        data.setSteamId(source.getSteamId());
        data.setTotalTime(source.getTotalTime());
        data.setTotalLaps(source.getTotalLaps());

        return data;
    }
}
