package com.sss.garage.converter.presence;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.race.Race;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class PresenceReverseConverter extends BaseConverter implements Converter<PresenceData, Presence> {
    @Override
    public Presence convert(final PresenceData source) {
        final Presence target = new Presence();

        target.setId(source.getId());
        target.setDriver(getConversionService().convert(source.getDriver(), Driver.class));
        target.setRace(getConversionService().convert(source.getRace(), Race.class));
        target.setIsPresent(source.getIsPresent());
        target.setDate(Date.from(Instant.now()));

        return target;
    }
}
