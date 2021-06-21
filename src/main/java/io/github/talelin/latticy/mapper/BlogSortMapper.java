package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.BlogSortDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSortMapper extends BaseMapper<BlogSortDO> {

    int deleteBatchByBlogIdAndSortId(@Param("blogId") Integer blogId, @Param("sortId") Integer sortId);
}
