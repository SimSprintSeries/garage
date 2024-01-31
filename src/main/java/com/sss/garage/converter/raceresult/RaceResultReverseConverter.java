package com.sss.garage.converter.raceresult;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.team.Team;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RaceResultReverseConverter extends BaseConverter implements Converter<RaceResultData, RaceResult> {

    @Override
    public RaceResult convert(final RaceResultData source) {

        final RaceResult target = new RaceResult();

        target.setId(source.getId());
        target.setDnf(source.getDnf());
        target.setDsq(source.getDsq());
        target.setFastestLap(source.getFastestLap());
        target.setFinishPosition(source.getFinishPosition());
        target.setPolePosition(source.getPolePosition());
        target.setDriver(getConversionService().convert(source.getDriver(), Driver.class));
        target.setComment(source.getComment());
        target.setRace(getConversionService().convert(source.getRace(), Race.class));
        target.setTeam(getConversionService().convert(source.getTeam(), Team.class));
        target.setPointsForPosition(source.getPointsForPosition());

        return target;
    }
}
