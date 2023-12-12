package com.sss.garage.converter.pointposition;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.pointposition.PointPositionData;
import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.model.pointposition.PointPosition;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PointPositionReverseConverter extends BaseConverter implements Converter<PointPositionData, PointPosition> {
    @Override
    public PointPosition convert(final PointPositionData source) {
        final PointPosition target = new PointPosition();

        target.setId(source.getId());
        target.setCategory(getConversionService().convert(source.getCategory(), PointCategory.class));
        target.setPosition(source.getPosition());
        target.setPoints(source.getPoints());

        return target;
    }
}
