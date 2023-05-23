package com.sss.garage.converter.split;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.model.split.Split;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SplitConverter extends BaseConverter implements Converter<Split, SplitData> {

    @Override
    public SplitData convert(final Split source) {
        final SplitData data = new SplitData();
        data.setId(source.getId());
        data.setName(source.getSplit());
        data.setLeague(getConversionService().convert(source.getLeague(), LeagueData.class));
        data.setDisplayText(data.getLeague().getDisplayText() + " - Split " + source.getSplit());
        data.setDrivers(data.getDrivers());
        return data;
    }
}
