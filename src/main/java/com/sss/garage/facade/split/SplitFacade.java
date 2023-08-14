package com.sss.garage.facade.split;


import com.sss.garage.data.split.SplitData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SplitFacade {
    List<SplitData> getAllSplits();

    SplitData getSplit(final Long id);

    void createSplit(final SplitData splitData);

    void deleteSplit(final Long id);

    Page<SplitData> getSplitsPaginated(final Pageable pageable);
}
