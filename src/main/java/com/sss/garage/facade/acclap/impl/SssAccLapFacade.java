package com.sss.garage.facade.acclap.impl;

import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.acclap.AccLapFacade;
import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.service.acclap.AccLapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssAccLapFacade extends SssBaseFacade implements AccLapFacade {
    private AccLapService lapService;

    @Override
    public AccLapData getLap(final Long id) {
        return lapService.getLap(id)
                .map(l -> conversionService.convert(l, AccLapData.class))
                .orElseThrow();
    }

    @Override
    public List<AccLapData> getAllLaps() {
        return lapService.getAllLaps().stream()
                .map(l -> conversionService.convert(l, AccLapData.class))
                .toList();
    }

    @Override
    public void createLap(final AccLapData lapData) {
        lapService.createLap(conversionService.convert(lapData, AccLap.class));
    }

    @Override
    public void deleteLap(final Long id) {
        lapService.deleteLap(id);
    }

    @Autowired
    public void setLapService(AccLapService lapService) {
        this.lapService = lapService;
    }
}
