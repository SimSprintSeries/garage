package com.sss.garage.dev.initial.data.legacy.converter;

import java.util.Optional;
import java.util.function.Predicate;

import com.sss.garage.dev.initial.data.legacy.model.LegacyDriver;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.user.DiscordUser;

import org.springframework.core.convert.converter.Converter;

public class LegacyDriverConverter implements Converter<LegacyDriver, Driver> {

    @Override
    public Driver convert(final LegacyDriver source) {
        final Driver driver = new Driver();

        Optional.ofNullable(source.discordUserId)
                .filter(Predicate.not(String::isBlank))
                .ifPresent(id -> {
            final DiscordUser dcUser = new DiscordUser();
            dcUser.setId(Long.valueOf(source.discordUserId));
            driver.setDiscordUser(dcUser);
        });
        driver.setName(source.name);
        return driver;
    }
}
