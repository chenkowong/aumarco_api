package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.BlogSortDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogSortMapper extends BaseMapper<BlogSortDO> {

    IPage<BlogSortDO> selectPageBySortId(Page<BlogSortDO> pager, Integer sortId, Integer removeId);

    List<Integer> selectSortIdsByBlogId(Integer id);

    List<BlogSortDO> selectBlogSortByBlogId(Integer id);

    int insertBatch(@Param("relations")List<BlogSortDO> relations);

    int deleteBatchByBlogIdAndSortId(@Param("blogId") Integer blogId, @Param("sortId") Integer sortId);
}
