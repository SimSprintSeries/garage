package com.sss.garage.converter.race;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.converter.event.EventReverseConverter;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.split.Split;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RaceReverseConverter extends BaseConverter implements Converter<RaceData, Race> {

    @Override
    public Race convert(final RaceData source) {
        final Race target = new Race();

        target.setSplit(getConversionService().convert(source.getSplit(), Split.class));
        target.setName(source.getName());

        return target;
    }
}
