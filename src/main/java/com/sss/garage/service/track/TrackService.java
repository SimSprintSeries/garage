package com.sss.garage.service.track;

import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    List<Track> getAllTracks();

    Optional<Track> getTrack(final Long id);

    void createTrack(final Track track);

    void deleteTrack(final Long id);

    Page<Track> getTracksPaginated(final Pageable pageable);
}
