package com.sss.garage.converter.pointcategory;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.pointcategory.PointCategoryData;
import com.sss.garage.data.pointposition.PointPositionData;
import com.sss.garage.model.pointcategory.PointCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PointCategoryConverter extends BaseConverter implements Converter<PointCategory, PointCategoryData> {
    @Override
    public PointCategoryData convert(final PointCategory source) {
        final PointCategoryData data = new PointCategoryData();

        data.setId(source.getId());
        data.setName(source.getName());
        data.setLeagues(source.getLeagues().stream().map(l -> getConversionService().convert(l, LeagueData.class)).collect(Collectors.toSet()));
        data.setLapPoints(source.getLapPoints());
        data.setPolePoints(source.getPolePoints());
        data.setPositions(source.getPositions().stream().map(p -> getConversionService().convert(p, PointPositionData.class)).collect(Collectors.toSet()));

        return data;
    }
}
