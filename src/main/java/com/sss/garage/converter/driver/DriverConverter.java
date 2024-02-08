package com.sss.garage.converter.driver;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.model.driver.Driver;

import com.sss.garage.service.elo.EloService;
import com.sss.garage.service.raceresult.RaceResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DriverConverter extends BaseConverter implements Converter<Driver, DriverData> {

    private EloService eloService;

    private RaceResultService raceResultService;

    @Override
    public DriverData convert(final Driver source) {
        final DriverData data = new DriverData();

        if (source.getDiscordUser() != null) {
            data.setDiscordName(source.getDiscordUser().getUsername() + "#" + source.getDiscordUser().getDiscriminator());
        }

        data.setId(source.getId());
        data.setNickname(source.getName());
        data.setElos(eloService.getAllElos(source).stream().map(e -> getConversionService().convert(e, EloData.class)).collect(Collectors.toSet()));
        data.setSplits(source.getSplits().stream().map(s -> getConversionService().convert(s, SplitData.class)).collect(Collectors.toSet()));
        data.setTotalWins(Optional.ofNullable(source.getTotalWins()).orElseGet(() -> raceResultService.calculateAndSaveTotalDriverWins(source)));
        data.setTotalTopTenResults(Optional.ofNullable(source.getTotalTopTenResults()).orElseGet(() -> raceResultService.calculateAndSaveTotalDriverTopTenResults(source)));
        data.setTotalRacesDriven(Optional.ofNullable(source.getTotalRacesDriven()).orElseGet(() -> raceResultService.calculateAndSaveTotalRacesDriven(source)));
        data.setPodiums(Optional.ofNullable(source.getPodiums()).orElseGet(() -> raceResultService.calculateAndSaveTotalPodiums(source)));
        data.setFastestLaps(Optional.ofNullable(source.getFastestLaps()).orElseGet(() -> raceResultService.calculateAndSaveFastestLaps(source)));
        data.setPolePositions(Optional.ofNullable(source.getPolePositions()).orElseGet(() -> raceResultService.calculateAndSavePolePositions(source)));

        return data;
    }

    @Autowired
    public void setEloService(final EloService eloService) {
        this.eloService = eloService;
    }

    @Autowired
    public void setRaceResultService(final RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }
}
