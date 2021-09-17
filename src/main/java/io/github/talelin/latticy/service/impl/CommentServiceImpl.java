package io.github.talelin.latticy.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.CommonUtil;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateCommentDTO;
import io.github.talelin.latticy.mapper.CommentMapper;
import io.github.talelin.latticy.model.CommentDO;
import io.github.talelin.latticy.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentDO> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public IPage<CommentDO> selectPageByBlogId(Integer page, Integer count, Integer blogId, Integer parentId, Date start, Date end) {
        Page<CommentDO> paper = new Page<>(page, count);
        IPage<CommentDO> iPage = commentMapper.selectPageByBlogId(paper, blogId, parentId, start, end);
        return iPage;
    }

    @Override
    public List<CommentDO> selectChildrenByParentId(Integer blogId, Integer parentId) {
        List<CommentDO> children = commentMapper.selectPageByBlogId(blogId, parentId, null, null);
        return children;
    }

    @Override
    public boolean createComment(CreateOrUpdateCommentDTO validator) {
        CommentDO comment = new CommentDO();
        BeanUtils.copyProperties(validator, comment);
        if (comment.getName() == null || comment.getName() == "") comment.setName(CommonUtil.getIdNumber("匿名访客"));
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return commentMapper.deleteById(id) > 0;
    }
}
