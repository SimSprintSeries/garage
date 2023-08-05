package com.sss.garage.service.acclap.impl;

import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.acclap.AccLapRepository;
import com.sss.garage.service.acclap.AccLapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssAccLapService implements AccLapService {
    private AccLapRepository lapRepository;

    @Override
    public Optional<AccLap> getLap(final Long id) {
        return lapRepository.findById(id);
    }

    @Override
    public List<AccLap> getAllLaps() {
        return lapRepository.findAll();
    }

    @Override
    public void createLap(final AccLap lap) {
        lapRepository.save(lap);
    }

    @Override
    public void deleteLap(final Long id) {
        lapRepository.deleteById(id);
    }

    @Autowired
    public void setLapRepository(AccLapRepository lapRepository) {
        this.lapRepository = lapRepository;
    }
}