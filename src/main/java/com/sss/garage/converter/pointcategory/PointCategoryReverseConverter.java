package com.sss.garage.converter.pointcategory;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.pointcategory.PointCategoryData;
import com.sss.garage.model.league.League;
import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.model.pointposition.PointPosition;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PointCategoryReverseConverter extends BaseConverter implements Converter<PointCategoryData, PointCategory> {
    @Override
    public PointCategory convert(final PointCategoryData source) {
        final PointCategory target = new PointCategory();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setLeagues(source.getLeagues().stream().map(l -> getConversionService().convert(l, League.class)).collect(Collectors.toSet()));
        target.setLapPoints(source.getLapPoints());
        target.setPolePoints(source.getPolePoints());
        target.setPositions(source.getPositions().stream().map(p -> getConversionService().convert(p, PointPosition.class)).collect(Collectors.toSet()));

        return target;
    }
}
