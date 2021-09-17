package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.blog.CreateOrUpdateCommentDTO;
import io.github.talelin.latticy.model.CommentDO;

import java.util.Date;
import java.util.List;

public interface CommentService {

    IPage<CommentDO> selectPageByBlogId(Integer page, Integer count, Integer blogId, Integer parentId, Date start, Date end);

    List<CommentDO> selectChildrenByParentId(Integer blogId, Integer parentId);

    boolean createComment(CreateOrUpdateCommentDTO validator);

    boolean deleteById(Integer id);
}
