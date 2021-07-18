package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.ExchangeOrderDO;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeOrderMapper extends BaseMapper<ExchangeOrderDO> {
    IPage<ExchangeOrderDO> selectPageByKeyWord(Page<ExchangeOrderDO> paper, Integer status, Integer requestUserId, Integer exchangeUserId);
}
