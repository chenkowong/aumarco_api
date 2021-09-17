package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.CommentDO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<CommentDO> {

    IPage<CommentDO> selectPageByBlogId(Page<CommentDO> paper, Integer blogId, Integer parentId, Date start, Date end);

    List<CommentDO> selectPageByBlogId(Integer blogId, Integer parentId, Date start, Date end);
}
