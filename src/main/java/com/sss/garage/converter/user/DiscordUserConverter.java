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
        data.setAvatar(source.getAvatarId());
        data.setBilew(source.getDiscriminator());
        data.setDisplayName(source.getDisplayName());
        data.setUsername(source.getUsername());
        Optional.ofNullable(source.getDriver())
                .ifPresent(d -> data.setDriverId(d.getId()));

        return data;
    }
}
