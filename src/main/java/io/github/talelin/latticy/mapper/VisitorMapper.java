package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.VisitorDO;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorMapper extends BaseMapper<VisitorDO> {
    VisitorDO selectVisitorByCip(String cip);

    IPage<VisitorDO> selectPageByKeyWord(Page<VisitorDO> paper, String keyWord);
}
