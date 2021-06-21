package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.sort.CreateOrUpdateSortDTO;
import io.github.talelin.latticy.model.SortDO;

import java.util.List;
import java.util.Map;

public interface SortService {
    SortDO getSortById(Integer id); // 通过id查询类别

    List<SortDO> findAll(); // 查询所有类别

    IPage<SortDO> selectPageByKeyWord(Integer page, Integer count, String keyWord); // 分页查询类别

    boolean createSort(CreateOrUpdateSortDTO validator); // 新增类别

    boolean updateSort(SortDO sort, CreateOrUpdateSortDTO validator); // 修改类别

    boolean deleteById(Integer id); // 删除

    SortDO selectSortByBlogId(Integer blogId);
}
