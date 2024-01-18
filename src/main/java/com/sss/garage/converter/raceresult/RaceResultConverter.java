package com.sss.garage.converter.raceresult;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.model.raceresult.RaceResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RaceResultConverter extends BaseConverter implements Converter<RaceResult, RaceResultData> {

    @Override
    public RaceResultData convert(final RaceResult source) {

        final RaceResultData data = new RaceResultData();

        data.setId(source.getId());
        data.setDnf(source.getDnf());
        data.setDsq(source.getDsq());
        data.setFastestLap(source.getFastestLap());
        data.setFinishPosition(source.getFinishPosition());
        data.setPolePosition(source.getPolePosition());
        data.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));
        data.setComment(source.getComment());
        //data.setRace(getConversionService().convert(source.getRace(), RaceData.class));
        data.setTeam(getConversionService().convert(source.getTeam(), TeamData.class));
        data.setPointsForPosition(source.getPointsForPosition());

        return data;
    }
}
