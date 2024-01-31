package com.sss.garage.service.split;

import com.sss.garage.model.split.Split;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SplitService {
    Optional<Split> getSplit(final Long id);

    void createSplit(final Split split);

    void deleteSplit(final Long id);

    Page<Split> getSplitsPaginated(final Pageable pageable);
}
