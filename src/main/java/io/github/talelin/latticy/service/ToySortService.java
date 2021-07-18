package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.toy_sort.RemoveToySortDTO;
import io.github.talelin.latticy.model.ToySortDO;

public interface ToySortService {
    boolean dispatchToySort(ToySortDO validator);

    boolean removeToySort(RemoveToySortDTO dto);
}
