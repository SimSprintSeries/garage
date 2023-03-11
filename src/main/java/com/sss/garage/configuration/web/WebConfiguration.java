package com.sss.garage.configuration.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sss.garage.converter.driver.DriverConverter;
import com.sss.garage.converter.elo.EloConverter;
import com.sss.garage.converter.event.EventConverter;
import com.sss.garage.converter.game.GameConverter;
import com.sss.garage.converter.jwt.JwtTokenConverter;
import com.sss.garage.converter.league.LeagueConverter;
import com.sss.garage.converter.race.RaceConverter;
import com.sss.garage.converter.split.SplitConverter;
import com.sss.garage.service.discord.converter.DiscordOAuth2ToUserConverter;
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
    private DiscordOAuth2ToUserConverter discordOAuth2ToUserConverter;
    private EloConverter eloConverter;
    private DriverConverter driverConverter;
    private GameConverter gameConverter;
    private EventConverter eventConverter;
    private LeagueConverter leagueConverter;
    private RaceConverter raceConverter;
    private SplitConverter splitConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(jwtTokenConverter);
        registry.addConverter(discordRoleConverter);
        registry.addConverter(discordOAuth2UserConverter);
        registry.addConverter(discordOAuth2ToUserConverter);

        registry.addConverter(eloConverter);
        registry.addConverter(driverConverter);
        registry.addConverter(gameConverter);
        registry.addConverter(eventConverter);
        registry.addConverter(leagueConverter);
        registry.addConverter(raceConverter);
        registry.addConverter(splitConverter);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
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

    @Autowired
    public void setDiscordOAuth2ToUserConverter(final DiscordOAuth2ToUserConverter discordOAuth2ToUserConverter) {
        this.discordOAuth2ToUserConverter = discordOAuth2ToUserConverter;
    }

    @Autowired
    public void setEloConverter(final EloConverter eloConverter) {
        this.eloConverter = eloConverter;
    }

    @Autowired
    public void setDriverConverter(final DriverConverter driverConverter) {
        this.driverConverter = driverConverter;
    }

    @Autowired
    public void setGameConverter(final GameConverter gameConverter) {
        this.gameConverter = gameConverter;
    }

    @Autowired
    public void setEventConverter(final EventConverter eventConverter) {
        this.eventConverter = eventConverter;
    }

    @Autowired
    public void setLeagueConverter(final LeagueConverter leagueConverter) {
        this.leagueConverter = leagueConverter;
    }

    @Autowired
    public void setRaceConverter(final RaceConverter raceConverter) {
        this.raceConverter = raceConverter;
    }

    @Autowired
    public void setSplitConverter(final SplitConverter splitConverter) {
        this.splitConverter = splitConverter;
    }
}
