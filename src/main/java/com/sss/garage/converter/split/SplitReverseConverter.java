package com.sss.garage.converter.split;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.model.league.League;
import com.sss.garage.model.split.Split;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SplitReverseConverter extends BaseConverter implements Converter<SplitData, Split> {

    @Override
    public Split convert(final SplitData source) {
        final Split target = new Split();

        target.setId(source.getId());
        target.setLeague(getConversionService().convert(source.getLeague(), League.class));
        target.setSplit(source.getName());

        return target;
    }
}
