package com.sss.garage.configuration.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sss.garage.controller.converter.jwt.JwtTokenConverter;
import com.sss.garage.service.discord.converter.DiscordOAuth2UserConverter;
import com.sss.garage.service.discord.converter.DiscordRoleConverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private JwtTokenConverter jwtTokenConverter;
    private DiscordRoleConverter discordRoleConverter;
    private DiscordOAuth2UserConverter discordOAuth2UserConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(jwtTokenConverter);
        registry.addConverter(discordRoleConverter);
        registry.addConverter(discordOAuth2UserConverter);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Autowired
    public void setJwtTokenConverter(final JwtTokenConverter jwtTokenConverter) {
        this.jwtTokenConverter = jwtTokenConverter;
    }

    @Autowired
    public void setDiscordRoleConverter(final DiscordRoleConverter discordRoleConverter) {
        this.discordRoleConverter = discordRoleConverter;
    }

    @Autowired
    public void setDiscordOAuth2UserConverter(final DiscordOAuth2UserConverter discordOAuth2UserConverter) {
        this.discordOAuth2UserConverter = discordOAuth2UserConverter;
    }
}
