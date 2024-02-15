package com.sss.garage.converter.driver;

import java.util.Optional;
import java.util.stream.Collectors;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.data.user.user.DiscordUserData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.service.elo.EloService;
import com.sss.garage.service.raceresult.RaceResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Base converter not registered in conversionService
 * To use directly (for example in infinite loop scenarios)
 * For example see {@link com.sss.garage.converter.elo.EloConverter}
 */
@Component
public class BasicDriverConverter extends BaseConverter implements Converter<Driver, DriverData> {

    @Override
    public DriverData convert(final Driver source) {
        final DriverData data = new DriverData();

        Optional.ofNullable(source.getDiscordUser()).ifPresent(u -> data.setDiscordUser(getConversionService().convert(u, DiscordUserData.class)));

        data.setId(source.getId());
        data.setNickname(source.getName()); // in case discord user is null

        return data;
    }
}
