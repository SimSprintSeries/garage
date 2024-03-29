package com.sss.garage.facade.track;

import com.sss.garage.data.track.TrackData;

import java.util.List;

public interface TrackFacade {
    List<TrackData> getAllTracks();

    TrackData getTrack(final Long id);

    void createTrack(final TrackData trackData);

    void deleteTrack(final Long id);
}
