package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.toy_sort.RemoveToySortDTO;
import io.github.talelin.latticy.mapper.ToySortMapper;
import io.github.talelin.latticy.model.ToySortDO;
import io.github.talelin.latticy.service.ToySortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToySortServiceImpl implements ToySortService {

    @Autowired
    private ToySortMapper toySortMapper;

    @Override
    public boolean dispatchToySort(ToySortDO validator) {
        return toySortMapper.insert(validator) > 0;
    }

    @Override
    public boolean removeToySort(RemoveToySortDTO dto) {
        return toySortMapper.deleteBatchByToyIdAndSortId(dto.getToyId(), dto.getSortId()) > 0;
    }
}
