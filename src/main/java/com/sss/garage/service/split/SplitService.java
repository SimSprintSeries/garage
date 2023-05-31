package com.sss.garage.service.split;

import com.sss.garage.model.split.Split;

import java.util.List;
import java.util.Optional;

public interface SplitService {
    List<Split> getAllSplits();

    Optional<Split> getSplit(final Long id);

    void createSplit(final Split split);

    void deleteSplit(final Long id);
}
