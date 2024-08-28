package com.sss.garage.facade.acclap.impl;

import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.acclap.AccLapFacade;
import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.league.League;
import com.sss.garage.model.track.Track;
import com.sss.garage.service.acclap.AccLapService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.track.TrackService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssAccLapFacade extends SssBaseFacade implements AccLapFacade {
    private AccLapService lapService;

    private TrackService trackService;

    private LeagueService leagueService;

    @Override
    public AccLapData getLap(final Long id) {
        return lapService.getLap(id)
                .map(l -> conversionService.convert(l, AccLapData.class))
                .orElseThrow();
    }

    @Override
    public Page<AccLapData> getFastestLapsForEveryDriver(final String sessionType, final String trackId,
                                                         final String leagueId, final String className, final Pageable pageable) {
        Track track = null;
        League league = null;
        if(Strings.isNotEmpty(trackId)) {
            track = trackService.getTrack(Long.valueOf(trackId)).orElseThrow();
        }
        if(Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        Page<AccLap> lap = lapService.getFastestLapsForEveryDriver(sessionType, track, league, className, pageable);
        return lap.map(l -> conversionService.convert(l, AccLapData.class));
    }

    @Autowired
    public void setLapService(AccLapService lapService) {
        this.lapService = lapService;
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setTrackService(final TrackService trackService) {
        this.trackService = trackService;
    }
}
