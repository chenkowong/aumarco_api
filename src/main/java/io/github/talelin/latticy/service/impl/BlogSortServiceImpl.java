package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.mapper.BlogSortMapper;
import io.github.talelin.latticy.model.BlogSortDO;
import io.github.talelin.latticy.service.BlogSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogSortServiceImpl implements BlogSortService {

    @Autowired
    private BlogSortMapper blogSortMapper;

    @Override
    public boolean dispatchBlogSort(BlogSortDO blogSortDO) {
        return blogSortMapper.insert(blogSortDO) > 0;
    }
}
