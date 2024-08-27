package com.sss.garage.service.acclap.impl;

import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.acclap.AccLapRepository;
import com.sss.garage.model.track.Track;
import com.sss.garage.service.acclap.AccLapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class SssAccLapService implements AccLapService {
    private AccLapRepository lapRepository;

    @Override
    public Optional<AccLap> getLap(final Long id) {
        return lapRepository.findById(id);
    }

    @Override
    public Page<AccLap> getFastestLapsForEveryDriver(final String sessionType, final Track track,
                                                     final String serverName, final String className, final Pageable pageable) {
        List<AccLap> laps = lapRepository.findAllByParams(sessionType, track, serverName);
        Set<String> steamIds = new HashSet<>();
        List<AccLap> bestLaps = new ArrayList<>();
        for (AccLap lap : laps) {
            if (!lap.getCarName().matches(".*(?i)" + className + ".*")) {
                if (className != null) {
                    continue;
                }
            }
            steamIds.add(lap.getSteamId());
        }
        for (String steamId : steamIds) {
            AccLap bestLap = new AccLap();
            Float bestLaptime = (float)100000;
            Float bestSector1 = (float)100000;
            Float bestSector2 = (float)100000;
            Float bestSector3 = (float)100000;
            Integer lapCount = 0;
            for (AccLap lap : laps) {
                if (lap.getSteamId().equals(steamId)) {
                    lapCount++;
                    if (Float.parseFloat(lap.getLaptime()) < bestLaptime) {
                        bestLaptime = Float.valueOf(lap.getLaptime());
                    }
                    if (Float.parseFloat(lap.getSector1()) < bestSector1) {
                        bestSector1 = Float.valueOf(lap.getSector1());
                    }
                    if (Float.parseFloat(lap.getSector2()) < bestSector2) {
                        bestSector2 = Float.valueOf(lap.getSector2());
                    }
                    if (Float.parseFloat(lap.getSector3()) < bestSector3) {
                        bestSector3 = Float.valueOf(lap.getSector3());
                    }
                    bestLap.setFirstName(lap.getFirstName());
                    bestLap.setLastName(lap.getLastName());
                    bestLap.setShortName(lap.getShortName());
                    bestLap.setCarName(lap.getCarName());
                    bestLap.setSteamId(steamId);
                    bestLap.setDriver(lap.getDriver());
                    bestLap.setLaptime(convertSecondsToMinutes(bestLaptime));
                    bestLap.setSector1(convertSecondsToMinutes(bestSector1));
                    bestLap.setSector2(convertSecondsToMinutes(bestSector2));
                    bestLap.setSector3(convertSecondsToMinutes(bestSector3));
                    bestLap.setTheoreticalBest(convertSecondsToMinutes(bestSector1 + bestSector2 + bestSector3));
                    bestLap.setValidLaps(lapCount);
                    bestLap.setTotalTime(convertSecondsToMinutes(Float.valueOf(lap.getTotalTime())));
                    bestLap.setTotalLaps(lap.getTotalLaps());
                }
            }
            bestLaps.add(bestLap);
        }
        if (sessionType.equalsIgnoreCase("R")) {
            bestLaps.sort(Comparator.comparing(AccLap::getTotalLaps).reversed()
                    .thenComparing((AccLap l) -> convertMinutesToSeconds(l.getTotalTime())));
        } else {
            bestLaps.sort(Comparator.comparing((AccLap l) -> convertMinutesToSeconds(l.getLaptime())));
        }
        Integer start = (int)pageable.getOffset();
        Integer end = Math.min((start + pageable.getPageSize()), bestLaps.size());
        return new PageImpl<>(bestLaps.subList(start, end), pageable, bestLaps.size());
    }

    private String convertSecondsToMinutes(final Float seconds) {
        DecimalFormat df = new DecimalFormat("#.000");
        if((int)(seconds/60) == 0) {
            return df.format(seconds % 60);
        } else {
            return (int)(seconds/60) + ":" + df.format(seconds % 60);
        }
    }

    private Float convertMinutesToSeconds(final String minutes) {
        String[] listed = minutes.split(":");
        return Float.parseFloat(listed[0]) * 60 + Float.parseFloat(listed[1].replace(",", "."));
    }

    @Autowired
    public void setLapRepository(AccLapRepository lapRepository) {
        this.lapRepository = lapRepository;
    }
}
