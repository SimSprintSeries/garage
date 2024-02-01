package com.sss.garage.service.discord.converter;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.auth.DiscordOAuth2User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscordOAuth2ToUserConverter implements Converter<DiscordOAuth2User, DiscordUser> {

    @Override
    public DiscordUser convert(final DiscordOAuth2User source) {
        final DiscordUser user = new DiscordUser();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setEmail(source.getAttribute("email"));
        user.setDiscriminator(source.getAttribute("discriminator"));
        user.setAvatarId(source.getAttribute("avatar"));
        user.setDisplayName(source.getAttribute("global_name"));

        return user;
    }
}
