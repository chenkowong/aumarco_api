package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.ToyUserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyUserMapper extends BaseMapper<ToyUserDO> {

    IPage<ToyUserDO> selectPageByUserId(Page<ToyUserDO> paper, Integer userId, Integer status);

    ToyUserDO selectByToyId(Integer toyId);

    int deleteBatchByToyIdAndUserId(@Param("toyId") Integer toyId, @Param("userId") Integer userId);
}
