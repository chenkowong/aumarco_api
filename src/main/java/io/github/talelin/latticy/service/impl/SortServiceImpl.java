package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.sort.CreateOrUpdateSortDTO;
import io.github.talelin.latticy.mapper.SortMapper;
import io.github.talelin.latticy.model.SortDO;
import io.github.talelin.latticy.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private SortMapper sortMapper;

    @Override
    public List<SortDO> findAll() {
        List<SortDO> books = sortMapper.selectList(null);
        return books;
    }

    @Override
    public IPage<SortDO> selectPageByKeyWord(Integer page, Integer count, String keyWord) {
        Page<SortDO> paper = new Page<>(page, count);
        IPage<SortDO> iPage = sortMapper.selectPageByKeyWord(paper, keyWord);
        return iPage;
    }

    @Override
    public SortDO getSortById(Integer id) {
        SortDO sort = sortMapper.selectById(id);
        return sort;
    }

    @Override
    public boolean createSort(CreateOrUpdateSortDTO validator) {
        SortDO sort = new SortDO();
        sort.setSortName(validator.getSortName());
        sort.setSortAlias(validator.getSortAlias());
        sort.setSortDescription(validator.getSortDescription());
        sort.setParentSortId(validator.getParentSortId());
        return sortMapper.insert(sort) > 0;
    }

    @Override
    public boolean updateSort(SortDO sort, CreateOrUpdateSortDTO validator) {
        sort.setSortName(validator.getSortName());
        sort.setSortAlias(validator.getSortAlias());
        sort.setSortDescription(validator.getSortDescription());
        sort.setParentSortId(validator.getParentSortId());
        return sortMapper.updateById(sort) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return sortMapper.deleteById(id) > 0;
    }

    @Override
    public SortDO selectSortByBlogId(Integer blogId) {
        SortDO sort = sortMapper.selectSortByBlogId(blogId);
        return sort;
    }
}
