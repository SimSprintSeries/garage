package com.sss.garage.configuration.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sss.garage.converter.driver.DriverConverter;
import com.sss.garage.converter.elo.EloConverter;
import com.sss.garage.converter.event.EventConverter;
import com.sss.garage.converter.game.GameConverter;
import com.sss.garage.converter.jwt.JwtTokenConverter;
import com.sss.garage.converter.league.DetailedLeagueConverter;
import com.sss.garage.converter.league.LeagueConverter;
import com.sss.garage.converter.race.RaceConverter;
import com.sss.garage.converter.split.SplitConverter;
import com.sss.garage.converter.user.DiscordUserConverter;
import com.sss.garage.data.user.user.DiscordUserData;
import com.sss.garage.service.discord.converter.DiscordOAuth2ToUserConverter;
import com.sss.garage.service.discord.converter.DiscordOAuth2UserConverter;
import com.sss.garage.service.discord.converter.DiscordRoleConverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

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
    private DetailedLeagueConverter detailedLeagueConverter;
    private RaceConverter raceConverter;
    private SplitConverter splitConverter;
    private DiscordUserConverter discordUserConverter;

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
        registry.addConverter(detailedLeagueConverter);
        registry.addConverter(raceConverter);
        registry.addConverter(splitConverter);
        registry.addConverter(discordUserConverter);
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

    @Bean
    public OpenAPI garageOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Garage API")
                        .description("Garage API for race league management")
                        .version("v0.0.1"));
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

    @Autowired
    public void setDetailedLeagueConverter(final DetailedLeagueConverter detailedLeagueConverter) {
        this.detailedLeagueConverter = detailedLeagueConverter;
    }

    @Autowired
    public void setDiscordUserConverter(final DiscordUserConverter discordUserConverter) {
        this.discordUserConverter = discordUserConverter;
    }
}
