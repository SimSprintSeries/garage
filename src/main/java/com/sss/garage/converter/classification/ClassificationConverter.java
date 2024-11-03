package com.sss.garage.converter.classification;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.classification.ClassificationData;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.model.classification.Classification;
import com.sss.garage.service.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClassificationConverter extends BaseConverter implements Converter<Classification, ClassificationData> {
    private RaceResultService raceResultService;

    @Override
    public ClassificationData convert(final Classification source) {
        final ClassificationData classificationData = new ClassificationData();

        classificationData.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));
        classificationData.setTeam(getConversionService().convert(source.getTeam(), TeamData.class));
        classificationData.setPoints(source.getPoints());
        classificationData.setRaceResults(raceResultService.getRaceResultsForLeague(source.getDriver(), source.getLeague())
                .stream().map(r -> getConversionService().convert(r, RaceResultData.class)).toList());

        return classificationData;
    }

    @Autowired
    public void setRaceResultService(final RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }
}
