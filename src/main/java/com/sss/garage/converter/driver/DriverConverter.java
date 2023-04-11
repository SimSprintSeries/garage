package com.sss.garage.converter.driver;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.model.driver.Driver;

import com.sss.garage.service.elo.EloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DriverConverter extends BaseConverter implements Converter<Driver, DriverData> {

    private EloService eloService;

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
        data.setElos(eloService.getAllElos(source).stream().map(e -> getConversionService().convert(e, EloData.class)).toList());

        return data;
    }

    @Autowired
    public void setEloService(final EloService eloService) {
        this.eloService = eloService;
    }
}
