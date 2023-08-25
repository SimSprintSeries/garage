package com.sss.garage.service.penalty.impl;

import com.sss.garage.model.penalty.Penalty;
import com.sss.garage.model.penalty.PenaltyRepository;
import com.sss.garage.service.penalty.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssPenaltyService implements PenaltyService {
    private PenaltyRepository penaltyRepository;

    @Override
    public Optional<Penalty> getPenalty(final Long id) {
        return penaltyRepository.findById(id);
    }

    @Override
    public List<Penalty> getAllPenalties() {
        return penaltyRepository.findAll();
    }

    @Override
    public void createPenalty(final Penalty penalty) {
        penaltyRepository.save(penalty);
    }

    @Override
    public void deletePenalty(final Long id) {
        penaltyRepository.deleteById(id);
    }

    @Override
    public Page<Penalty> getPenaltiesPaginated(final Pageable pageable) {
        return penaltyRepository.findAll(pageable);
    }

    @Override
    public Page<Penalty> getPenaltiesPaginated(final Boolean checked, final Pageable pageable) {
        return penaltyRepository.findAllByChecked(checked, pageable);
    }

    @Autowired
    public void setPenaltyRepository(final PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }
}
