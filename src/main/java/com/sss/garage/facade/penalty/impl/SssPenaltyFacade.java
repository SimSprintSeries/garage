package com.sss.garage.facade.penalty.impl;

import com.sss.garage.data.penalty.PenaltyData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.penalty.PenaltyFacade;
import com.sss.garage.model.penalty.Penalty;
import com.sss.garage.service.penalty.PenaltyService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssPenaltyFacade extends SssBaseFacade implements PenaltyFacade {
    private PenaltyService penaltyService;

    @Override
    public PenaltyData getPenalty(final Long id) {
        return penaltyService.getPenalty(id)
                .map(p -> conversionService.convert(p, PenaltyData.class))
                .orElseThrow();
    }

    @Override
    public void createPenalty(final PenaltyData penalty) {
        penaltyService.createPenalty(conversionService.convert(penalty, Penalty.class));
    }

    @Override
    public void deletePenalty(final Long id) {
        penaltyService.deletePenalty(id);
    }

    @Override
    public Page<PenaltyData> getPenaltiesPaginated(final Boolean checked, final Pageable pageable) {
        Page<Penalty> penalty;
        if(checked != null) {
            penalty = penaltyService.getPenaltiesPaginated(checked, pageable);
        }
        else {
            penalty = penaltyService.getPenaltiesPaginated(pageable);
        }
        return penalty.map(p -> conversionService.convert(p, PenaltyData.class));
    }

    @Autowired
    public void setPenaltyService(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }
}
