package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.BlogVisitorDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogVisitorMapper extends BaseMapper<BlogVisitorDO> {
    BlogVisitorDO selectBlogVisitorByBlogIdAndVisitorId(
            @Param("blogId") Integer blogId,
            @Param("visitorId") Integer visitorId
    );

    IPage<BlogVisitorDO> selectPageByBlogId(Page<BlogVisitorDO> paper, Integer blogId);
}
