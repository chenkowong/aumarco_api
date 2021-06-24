package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.VisitorDO;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorMapper extends BaseMapper<VisitorDO> {
    VisitorDO selectVisitorByCip(String cip);
}
