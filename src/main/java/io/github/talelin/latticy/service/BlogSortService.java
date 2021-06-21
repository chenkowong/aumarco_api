package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.blog_sort.RemoveBlogSortDTO;
import io.github.talelin.latticy.model.BlogSortDO;

public interface BlogSortService {
    /**
     * 匹配博客-类别关联
     * @param validator
     * @return
     */
    boolean dispatchBlogSort(BlogSortDO validator);

    boolean removeBlogSort(RemoveBlogSortDTO dto);
}
