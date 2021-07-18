package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.ToySortDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToySortMapper extends BaseMapper<ToySortDO> {
    int deleteBatchByToyIdAndSortId(@Param("toyId") Integer toyId, @Param("sortId") Integer sortId);
}
