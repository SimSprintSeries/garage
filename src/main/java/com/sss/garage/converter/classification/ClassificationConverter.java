package com.sss.garage.converter.classification;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.classification.ClassificationData;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.model.classification.Classification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClassificationConverter extends BaseConverter implements Converter<Classification, ClassificationData> {
    @Override
    public ClassificationData convert(final Classification source) {
        final ClassificationData classificationData = new ClassificationData();

        classificationData.setDriver(getConversionService().convert(source.getDriver(), DriverData.class));
        classificationData.setTeam(getConversionService().convert(source.getTeam(), TeamData.class));
        classificationData.setPoints(source.getPoints());

        return classificationData;
    }
}
