package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.BlogSortDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogSortMapper extends BaseMapper<BlogSortDO> {

    IPage<BlogSortDO> selectPageBySortId(Page<BlogSortDO> pager, Integer sortId);

    int deleteBatchByBlogIdAndSortId(@Param("blogId") Integer blogId, @Param("sortId") Integer sortId);
}
