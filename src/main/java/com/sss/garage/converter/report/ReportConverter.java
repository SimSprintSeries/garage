package com.sss.garage.converter.report;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.report.ReportData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.model.report.Report;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReportConverter extends BaseConverter implements Converter<Report, ReportData> {
    @Override
    public ReportData convert(final Report source) {
        final ReportData data = new ReportData();

        data.setId(source.getId());
        data.setPenaltyPoints(source.getPenaltyPoints());
        data.setPenaltySeconds(source.getPenaltySeconds());
        data.setIncidentLink(source.getIncidentLink());
        data.setIncidentDescription(source.getIncidentDescription());
        data.setDecisionDescription(source.getDecisionDescription());
        data.setReportDate(source.getReportDate());
        data.setReportingDriver(getConversionService().convert(source.getReportingDriver(), DriverData.class));
        data.setReportedDriver(getConversionService().convert(source.getReportedDriver(), DriverData.class));
        data.setRace(getConversionService().convert(source.getRace(), RaceData.class));
        data.setChecked(source.getChecked());

        return data;
    }
}
