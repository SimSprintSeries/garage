package com.sss.garage.service.track;

import com.sss.garage.model.track.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    List<Track> getAllTracks();

    Optional<Track> getTrack(final Long id);

    void createTrack(final Track track);

    void deleteTrack(final Long id);
}
