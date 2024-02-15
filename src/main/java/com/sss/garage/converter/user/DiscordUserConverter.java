package com.sss.garage.converter.user;

import java.util.Optional;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.user.user.DiscordUserData;
import com.sss.garage.model.user.DiscordUser;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscordUserConverter extends BaseConverter implements Converter<DiscordUser, DiscordUserData> {

    @Override
    public DiscordUserData convert(final DiscordUser source) {
        final DiscordUserData data = new DiscordUserData();

        data.setId(source.getId());
        Optional.ofNullable(source.getAvatarId()).ifPresent(data::setAvatar);
        Optional.ofNullable(source.getDiscriminator()).ifPresent(data::setBilew);
        Optional.ofNullable(source.getDisplayName()).ifPresent(data::setDisplayName);
        Optional.ofNullable(source.getUsername()).ifPresent(data::setUsername);
        Optional.ofNullable(source.getDriver()).ifPresent(d -> data.setDriverId(d.getId())); // Do not change to Driver converter. Will cause infinite loop

        return data;
    }
}
