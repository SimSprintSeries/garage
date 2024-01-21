package com.sss.garage.converter.report;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.report.ReportData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.report.Report;
import com.sss.garage.model.race.Race;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReportReverseConverter extends BaseConverter implements Converter<ReportData, Report> {
    @Override
    public Report convert(final ReportData source) {
        final Report target = new Report();

        target.setId(source.getId());
        target.setPenaltySeconds(source.getPenaltySeconds());
        target.setPenaltyPoints(source.getPenaltyPoints());
        target.setIncidentLink(source.getIncidentLink());
        target.setIncidentDescription(source.getIncidentDescription());
        target.setDecisionDescription(source.getDecisionDescription());
        target.setReportDate(source.getReportDate());
        target.setReportingDriver(getConversionService().convert(source.getReportingDriver(), Driver.class));
        target.setReportedDriver(getConversionService().convert(source.getReportedDriver(), Driver.class));
        target.setRace(getConversionService().convert(source.getRace(), Race.class));
        target.setChecked(source.getChecked());

        return target;
    }


}
