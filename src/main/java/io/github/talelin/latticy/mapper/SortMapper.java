package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.SortDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortMapper extends BaseMapper<SortDO> {
    int insertBatch(@Param("relations") List<SortDO> relations);

    IPage<SortDO> selectPageByKeyWord(Page<SortDO> paper, String keyWord);
}
