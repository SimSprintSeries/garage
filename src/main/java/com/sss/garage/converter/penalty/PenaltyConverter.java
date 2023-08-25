package com.sss.garage.converter.penalty;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.penalty.PenaltyData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.model.penalty.Penalty;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PenaltyConverter extends BaseConverter implements Converter<Penalty, PenaltyData> {
    @Override
    public PenaltyData convert(final Penalty source) {
        final PenaltyData data = new PenaltyData();

        data.setId(source.getId());
        data.setPenaltyPoints(source.getPenaltyPoints());
        data.setPenaltySeconds(source.getPenaltySeconds());
        data.setIncidentLink(source.getIncidentLink());
        data.setIncidentDescription(source.getIncidentDescription());
        data.setDecisionDescription(source.getDecisionDescription());
        data.setReportDate(source.getReportDate());
        data.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));
        data.setRace(getConversionService().convert(source.getRace(), RaceData.class));
        data.setChecked(source.getChecked());

        return data;
    }
}
