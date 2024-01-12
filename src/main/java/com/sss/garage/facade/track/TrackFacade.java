package com.sss.garage.facade.track;

import com.sss.garage.data.track.TrackData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrackFacade {
    TrackData getTrack(final Long id);

    void createTrack(final TrackData trackData);

    void deleteTrack(final Long id);

    Page<TrackData> getTracksPaginated(final String name, final String country, final Pageable pageable);
}
