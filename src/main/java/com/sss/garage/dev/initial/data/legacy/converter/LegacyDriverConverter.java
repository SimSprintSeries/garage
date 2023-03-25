package com.sss.garage.dev.initial.data.legacy.converter;

import java.util.*;

import com.sss.garage.dev.initial.data.legacy.model.LegacyDriver;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.user.DiscordUser;

public class LegacyDriverConverter {

    public Driver convert(final LegacyDriver source, final Set<DiscordUser> discordUsers) {
        final Driver driver = new Driver();

        discordUsers.stream()
                .filter(u -> u.getId().equals(source.discordUserId))
                .findFirst()
                .ifPresent(driver::setDiscordUser);

        driver.setName(source.name);
        return driver;
    }
}
