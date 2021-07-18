package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.order.CreateOrUpdateExchangeOrder;
import io.github.talelin.latticy.model.ExchangeOrderDO;

public interface ExchangeOrderService {

    IPage<ExchangeOrderDO> selectPageByKeyWord(Integer page, Integer count, Integer status, Integer requestUserId, Integer exchangeUserId);

    ExchangeOrderDO selectById(Integer id);

    boolean createExchangeOrder(CreateOrUpdateExchangeOrder validator);

    boolean updateExchangeOrder(ExchangeOrderDO order, CreateOrUpdateExchangeOrder validator);

    boolean switchExchangeOrderStatus(ExchangeOrderDO order, CreateOrUpdateExchangeOrder validator);

    boolean deleteById(Integer blogId);
}
