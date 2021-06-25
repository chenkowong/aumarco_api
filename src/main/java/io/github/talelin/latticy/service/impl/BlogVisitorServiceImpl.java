package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.blog_visitor.DispatchBlogVisitorDTO;
import io.github.talelin.latticy.dto.blog_visitor.UpdateBlogVisitorDTO;
import io.github.talelin.latticy.mapper.BlogVisitorMapper;
import io.github.talelin.latticy.model.BlogVisitorDO;
import io.github.talelin.latticy.service.BlogVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogVisitorServiceImpl implements BlogVisitorService {

    @Autowired
    private BlogVisitorMapper blogVisitorMapper;

    @Override
    public IPage<BlogVisitorDO> selectPageByBlogId(Integer page, Integer count, Integer blogId) {
        Page<BlogVisitorDO> pager = new Page<>(page, count);
        IPage<BlogVisitorDO> iPage = blogVisitorMapper.selectPageByBlogId(pager, blogId);
        return iPage;
    }

    @Override
    public boolean dispatchBlogVisitor(BlogVisitorDO blogVisitorDO) {
        return blogVisitorMapper.insert(blogVisitorDO) > 0;
    };

    @Override
    public BlogVisitorDO selectBlogVisitor(DispatchBlogVisitorDTO dto) {
        return blogVisitorMapper.selectBlogVisitorByBlogIdAndVisitorId(dto.getBlogId(), dto.getVisitorId());
    }

    @Override
    public boolean updateBlogVisitor(BlogVisitorDO blogVisitor, UpdateBlogVisitorDTO validator) {
        blogVisitor.setCount(validator.getCount());
        return blogVisitorMapper.updateById(blogVisitor) > 0;
    }
}
