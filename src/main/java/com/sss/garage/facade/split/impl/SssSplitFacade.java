package com.sss.garage.facade.split.impl;

import com.sss.garage.data.split.SplitData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.split.SplitFacade;
import com.sss.garage.model.split.Split;
import com.sss.garage.service.split.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssSplitFacade extends SssBaseFacade implements SplitFacade {

    private SplitService splitService;

    @Override
    public List<SplitData> getAllSplits() {
        return splitService.getAllSplits().stream().map(s -> conversionService.convert(s, SplitData.class)).toList();
    }

    @Override
    public SplitData getSplit(final Long id) {
        return splitService.getSplit(id).map(s -> conversionService.convert(s, SplitData.class)).orElseThrow();
    }

    @Override
    public void createSplit(final SplitData splitData) {
        splitService.createSplit(conversionService.convert(splitData, Split.class));
    }

    @Override
    public void deleteSplit(final Long id) {
        splitService.deleteSplit(id);
    }

    @Override
    public Page<SplitData> getSplitsPaginated(final Pageable pageable) {
        Page<Split> split = splitService.getSplitsPaginated(pageable);
        return split.map(s -> conversionService.convert(s, SplitData.class));
    }

    @Autowired
    public void setSplitService(SplitService splitService) {
        this.splitService = splitService;
    }
}
