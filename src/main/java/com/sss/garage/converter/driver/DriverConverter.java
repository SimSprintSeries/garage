package com.sss.garage.converter.driver;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.model.driver.Driver;

import com.sss.garage.model.game.Game;
import com.sss.garage.service.elo.EloService;
import com.sss.garage.service.event.EventService;
import com.sss.garage.service.game.GameService;
import com.sss.garage.service.raceresult.RaceResultService;

import com.sss.garage.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DriverConverter extends BasicDriverConverter implements Converter<Driver, DriverData> {

    private EloService eloService;

    private RaceResultService raceResultService;

    private ReportService reportService;

    private GameService gameService;

    private EventService eventService;

    @Override
    public DriverData convert(final Driver source) {
        final DriverData data = super.convert(source); // id, nickname, discordValues

        data.setElos(eloService.getAllElos(source).stream().map(e -> getConversionService().convert(e, EloData.class)).collect(Collectors.toSet()));
        data.setSplits(source.getSplits().stream().map(s -> getConversionService().convert(s, SplitData.class)).collect(Collectors.toSet()));
        data.setTotalWins(Optional.ofNullable(source.getTotalWins()).orElseGet(() -> raceResultService.calculateAndSaveTotalDriverWins(source)));
        data.setTotalTopTenResults(Optional.ofNullable(source.getTotalTopTenResults()).orElseGet(() -> raceResultService.calculateAndSaveTotalDriverTopTenResults(source)));
        data.setTotalRacesDriven(Optional.ofNullable(source.getTotalRacesDriven()).orElseGet(() -> raceResultService.calculateAndSaveTotalRacesDriven(source)));
        data.setPodiums(Optional.ofNullable(source.getPodiums()).orElseGet(() -> raceResultService.calculateAndSaveTotalPodiums(source)));
        data.setFastestLaps(Optional.ofNullable(source.getFastestLaps()).orElseGet(() -> raceResultService.calculateAndSaveFastestLaps(source)));
        data.setPolePositions(Optional.ofNullable(source.getPolePositions()).orElseGet(() -> raceResultService.calculateAndSavePolePositions(source)));
        data.setPenaltyPointsF1(setPenaltyPoints(source, gameService.getGameFamilyByName("F1")));
        data.setPenaltyPointsAC(setPenaltyPoints(source, gameService.getGameFamilyByName("AC")));

        return data;
    }

    private Integer setPenaltyPoints(final Driver driver, final Game gameFamily) {
        LocalDate localDate = eventService.get10thEventDateByGameFamily(gameFamily)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        TemporalField dayOfWeek = WeekFields.ISO.dayOfWeek();

        return reportService.getPenaltyPoints(driver, gameFamily,
                Date.from(localDate.with(dayOfWeek, dayOfWeek.range().getMinimum())
                        .atStartOfDay().toInstant(ZoneOffset.UTC)));
    }

    @Autowired
    public void setEloService(final EloService eloService) {
        this.eloService = eloService;
    }

    @Autowired
    public void setRaceResultService(final RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @Autowired
    public void setReportService(final ReportService reportService) {
        this.reportService = reportService;
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }
}
