package com.sss.garage.service.auth;

import com.sss.garage.model.user.auth.DiscordOAuth2User;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2DiscordUserService extends DefaultOAuth2UserService {
    private ConversionService conversionService;
    private DiscordApiService discordApiService;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        final DiscordOAuth2User discordUser = conversionService.convert(oAuth2User, DiscordOAuth2User.class);
        discordUser.setRoles(discordApiService.findAllRolesForUserId(discordUser.getId()));
        return discordUser;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setDiscordApiService(final DiscordApiService discordApiService) {
        this.discordApiService = discordApiService;
    }
}
