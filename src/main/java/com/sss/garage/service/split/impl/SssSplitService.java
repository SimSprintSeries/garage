package com.sss.garage.service.split.impl;

import com.sss.garage.model.split.Split;
import com.sss.garage.model.split.SplitRepository;
import com.sss.garage.service.split.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssSplitService implements SplitService {

    private SplitRepository splitRepository;

    @Override
    public List<Split> getAllSplits() {
        return splitRepository.findAll();
    }

    @Override
    public Optional<Split> getSplit(final Long id) {
        return splitRepository.findById(id);
    }

    @Override
    public void createSplit(final Split split) {
        splitRepository.save(split);
    }

    @Override
    public void deleteSplit(final Long id) {
        splitRepository.deleteById(id);
    }

    @Override
    public Page<Split> getSplitsPaginated(final Pageable pageable) {
        return splitRepository.findAll(pageable);
    }

    @Autowired
    public void setSplitRepository(SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }
}
