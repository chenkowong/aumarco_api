package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateBlogDTO;
import io.github.talelin.latticy.model.BlogDO;

import java.util.Map;

public interface BlogService {

    IPage<BlogDO> selectPageByKeyWord(Integer page, Integer count, String keyWord);

    BlogDO selectById(Integer id);

    BlogDO selectBlogById(Integer id);

    boolean createBlog(CreateOrUpdateBlogDTO validator);

    boolean updateBlog(BlogDO blog, CreateOrUpdateBlogDTO validator);

    boolean updateBlogViews(BlogDO blog, CreateOrUpdateBlogDTO validator);

    boolean deleteById(Integer blogId);
}
