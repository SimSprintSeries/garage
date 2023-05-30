package com.sss.garage.facade.split;


import com.sss.garage.data.split.SplitData;

import java.util.List;

public interface SplitFacade {
    List<SplitData> getAllSplits();

    SplitData getSplit(final Long id);

    void createSplit(final SplitData splitData);

    void deleteSplit(final Long id);
}
