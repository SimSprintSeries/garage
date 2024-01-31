package com.sss.garage.converter.acclap;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.model.acclap.AccLap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccLapReverseConverter extends BaseConverter implements Converter<AccLapData, AccLap> {
    @Override
    public AccLap convert(final AccLapData source) {
        final AccLap target = new AccLap();

        target.setId(source.getId());
        target.setSector1(source.getSector1());
        target.setSector2(source.getSector2());
        target.setSector3(source.getSector3());
        target.setLaptime(source.getLaptime());
        target.setIsValidForBest(source.getIsValidForBest());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setShortName(source.getShortName());
        target.setRaceNumber(source.getRaceNumber());
        target.setCarModel(source.getCarModel());
        target.setCarName(source.getCarName());
        target.setTrackName(source.getTrackName());
        target.setSessionType(source.getSessionType());
        target.setServerName(source.getServerName());
        target.setStartDate(source.getStartDate());
        target.setLapCount(source.getLapCount());
        target.setTheoreticalBest(source.getTheoreticalBest());

        return target;
    }
}
