package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.ToyDO;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyMapper extends BaseMapper<ToyDO> {
    IPage<ToyDO> selectPageByKeyWord(Page<ToyDO> paper, String keyWord);
}
