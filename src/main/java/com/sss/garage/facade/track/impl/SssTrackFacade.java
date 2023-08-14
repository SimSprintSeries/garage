package com.sss.garage.facade.track.impl;

import com.sss.garage.data.track.TrackData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.track.TrackFacade;
import com.sss.garage.model.track.Track;
import com.sss.garage.service.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssTrackFacade extends SssBaseFacade implements TrackFacade {

    private TrackService trackService;

    @Override
    public List<TrackData> getAllTracks() {
        return trackService.getAllTracks().stream().map(t -> conversionService.convert(t, TrackData.class)).toList();
    }

    @Override
    public TrackData getTrack(final Long id) {
        return trackService.getTrack(id).map(t -> conversionService.convert(t, TrackData.class)).orElseThrow();
    }

    @Override
    public void createTrack(TrackData trackData) {
        trackService.createTrack(conversionService.convert(trackData, Track.class));
    }

    @Override
    public void deleteTrack(Long id) {
        trackService.deleteTrack(id);
    }

    @Override
    public Page<TrackData> getTracksPaginated(final Pageable pageable) {
        Page<Track> track = trackService.getTracksPaginated(pageable);
        return track.map(t -> conversionService.convert(t, TrackData.class));
    }

    @Autowired
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
