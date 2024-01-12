package com.sss.garage.service.track.impl;

import com.sss.garage.model.track.Track;
import com.sss.garage.model.track.TrackRepository;
import com.sss.garage.service.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssTrackService implements TrackService {

    private TrackRepository trackRepository;

    @Override
    public Optional<Track> getTrack(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public void createTrack(final Track track) {
        trackRepository.save(track);
    }

    @Override
    public void deleteTrack(final Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public Page<Track> getTracksPaginated(final String name, final String country, final Pageable pageable) {
        return trackRepository.findAllByParams(name, country, pageable);
    }

    @Autowired
    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }
}
