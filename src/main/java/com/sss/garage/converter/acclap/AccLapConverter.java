package com.sss.garage.converter.acclap;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.model.acclap.AccLap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccLapConverter extends BaseConverter implements Converter<AccLap, AccLapData> {
    @Override
    public AccLapData convert(final AccLap source) {
        final AccLapData data = new AccLapData();

        data.setId(source.getId());
        data.setSector1(source.getSector1());
        data.setSector2(source.getSector2());
        data.setSector3(source.getSector3());
        data.setLaptime(source.getLaptime());
        data.setIsValidForBest(source.getIsValidForBest());
        data.setFirstName(source.getFirstName());
        data.setLastName(source.getLastName());
        data.setShortName(source.getShortName());
        data.setRaceNumber(source.getRaceNumber());
        data.setCarModel(source.getCarModel());
        data.setCarName(source.getCarName());
        data.setTrackName(source.getTrackName());
        data.setSessionType(source.getSessionType());
        data.setServerName(source.getServerName());
        data.setStartDate(source.getStartDate());
        data.setLapCount(source.getLapCount());
        data.setTheoreticalBest(source.getTheoreticalBest());

        return data;
    }
}
