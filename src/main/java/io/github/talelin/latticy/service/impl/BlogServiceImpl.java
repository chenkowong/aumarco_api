package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateBlogDTO;
import io.github.talelin.latticy.mapper.BlogMapper;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.BlogSortDO;
import io.github.talelin.latticy.model.SortDO;
import io.github.talelin.latticy.service.BlogService;
import io.github.talelin.latticy.service.BlogSortService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogDO> implements BlogService {

    @Autowired
    private BlogSortService blogSortService;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public IPage<BlogDO> selectPageByKeyWord(Integer page, Integer count, String keyWord) {
        Page<BlogDO> pager = new Page<>(page, count);
        IPage<BlogDO> iPage = blogMapper.selectPageByKeyWord(pager, keyWord);
        return iPage;
    }

    @Override
    public BlogDO selectBlogById(Integer id) {
        BlogDO blog = blogMapper.selectBlogById(id);
        return blog;
    }

    @Override
    public BlogDO selectById(Integer id) {
        BlogDO blog = blogMapper.selectById(id);
        return blog;
    }

    @Override
    public boolean createBlog(CreateOrUpdateBlogDTO validator) {
        BlogDO blog = new BlogDO();
        blog.setUserId(validator.getUserId());
        blog.setBlogTitle(validator.getBlogTitle());
        blog.setBlogCover(validator.getBlogCover());
        blog.setBlogMarkdown(validator.getBlogMarkdown());
        blog.setBlogContent(validator.getBlogContent());
        blog.setBlogViews(validator.getBlogViews());
        blog.setBlogLikeCount(validator.getBlogLikeCount());
        blog.setBlogCommentCount(validator.getBlogCommentCount());
        save(blog);
        // 注入blog-sort中间件
        BlogSortDO blogSort = new BlogSortDO();
        blogSort.setBlogId(blog.getId());
        blogSort.setSortId(validator.getSortId());
        blogSortService.dispatchBlogSort(blogSort);
        return true;
    }

    @Override
    public boolean updateBlog(BlogDO blog, CreateOrUpdateBlogDTO validator) {
        blog.setBlogTitle(validator.getBlogTitle());
        blog.setBlogCover(validator.getBlogCover());
        blog.setBlogMarkdown(validator.getBlogMarkdown());
        blog.setBlogContent(validator.getBlogContent());
        return blogMapper.updateById(blog) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return blogMapper.deleteById(id) > 0;
    }
}
