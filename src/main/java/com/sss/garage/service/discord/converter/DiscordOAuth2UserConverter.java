package com.sss.garage.service.discord.converter;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.auth.DiscordOAuth2User;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class DiscordOAuth2UserConverter implements Converter<OAuth2User, DiscordOAuth2User> {

    @Override
    public DiscordOAuth2User convert(final OAuth2User source) {
        final DiscordOAuth2User discordUser = new DiscordOAuth2User();
        discordUser.setId(source.getAttribute("id"));
        discordUser.setUsername(source.getAttribute("username"));
        // more possible values: avatar, discriminator :bilew:, public_flags, banner, locale, mfa enabled, premium type, verified

        return discordUser;
    }
}
