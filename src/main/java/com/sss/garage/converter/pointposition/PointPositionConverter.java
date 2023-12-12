package com.sss.garage.converter.pointposition;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.pointcategory.PointCategoryData;
import com.sss.garage.data.pointposition.PointPositionData;
import com.sss.garage.model.pointposition.PointPosition;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PointPositionConverter extends BaseConverter implements Converter<PointPosition, PointPositionData> {
    @Override
    public PointPositionData convert(final PointPosition source) {
        final PointPositionData data = new PointPositionData();

        data.setId(source.getId());
        data.setCategory(getConversionService().convert(source.getCategory(), PointCategoryData.class));
        data.setPosition(source.getPosition());
        data.setPoints(source.getPoints());

        return data;
    }
}
