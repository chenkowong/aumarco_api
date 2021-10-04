package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.blog_sort.RemoveBlogSortDTO;
import io.github.talelin.latticy.model.BlogSortDO;

public interface BlogSortService {

    IPage<BlogSortDO> selectPageBySortId(Integer page, Integer count, Integer sortId, Integer removeId, Integer desc);

    /**
     * 匹配博客-类别关联
     * @param validator
     * @return
     */
    boolean dispatchBlogSort(BlogSortDO validator);

    boolean removeBlogSort(RemoveBlogSortDTO dto);
}
