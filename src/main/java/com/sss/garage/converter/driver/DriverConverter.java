package com.sss.garage.converter.driver;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.model.driver.Driver;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DriverConverter extends BaseConverter implements Converter<Driver, DriverData> {

    @Override
    public DriverData convert(final Driver source) {
        final DriverData data = new DriverData();

        String discordUsername = null;

        if (source.getDiscordUser() != null) {
            discordUsername = source.getDiscordUser().getUsername() + "#" + source.getDiscordUser().getDiscriminator();
        }

        data.setId(source.getId());
        data.setNickname(source.getName());
        data.setDiscordName(discordUsername);
        data.setElos(source.getElos());

        return data;
    }
}
