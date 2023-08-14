package com.sss.garage.converter.penalty;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.penalty.PenaltyData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.penalty.Penalty;
import com.sss.garage.model.race.Race;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PenaltyReverseConverter extends BaseConverter implements Converter<PenaltyData, Penalty> {
    @Override
    public Penalty convert(final PenaltyData source) {
        final Penalty target = new Penalty();

        target.setPenaltySeconds(source.getPenaltySeconds());
        target.setPenaltyPoints(source.getPenaltyPoints());
        target.setIncidentLink(source.getIncidentLink());
        target.setIncidentDescription(source.getIncidentDescription());
        target.setDecisionDescription(source.getDecisionDescription());
        target.setReportDate(source.getReportDate());
        target.setDriver(getConversionService().convert(source.getDriver(), Driver.class));
        target.setRace(getConversionService().convert(source.getRace(), Race.class));

        return target;
    }


}
