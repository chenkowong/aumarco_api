package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.common.mybatis.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.dto.order.CreateOrUpdateExchangeOrder;
import io.github.talelin.latticy.mapper.ExchangeOrderMapper;
import io.github.talelin.latticy.model.ExchangeOrderDO;
import io.github.talelin.latticy.service.ExchangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExchangeOrderServiceImpl extends ServiceImpl<ExchangeOrderMapper, ExchangeOrderDO> implements ExchangeOrderService {

    @Autowired
    private ExchangeOrderMapper exchangeOrderMapper;

    @Override
    public IPage<ExchangeOrderDO> selectPageByKeyWord(Integer page, Integer count, Integer status, Integer requestUserId, Integer exchangeUserId) {
        Page<ExchangeOrderDO> paper = new Page<>(page, count);
        IPage<ExchangeOrderDO> iPage = exchangeOrderMapper.selectPageByKeyWord(paper, status, requestUserId, exchangeUserId);
        return iPage;
    }

    @Override
    public ExchangeOrderDO selectById(Integer id) {
        ExchangeOrderDO order = exchangeOrderMapper.selectById(id);
        return order;
    }

    @Override
    public boolean createExchangeOrder(CreateOrUpdateExchangeOrder validator) {
        if (validator.getRequestUserId() == validator.getExchangeUserId()) {
            throw new NotFoundException("不能交换自己的宝贝哦");
        }
        ExchangeOrderDO order = new ExchangeOrderDO();
        order.setOrderNo('E' + validator.getRequestUserId() + String.valueOf(new Date().getTime()));
        order.setRequestUserId(validator.getRequestUserId());
        order.setRequestToyId(validator.getRequestToyId());
        order.setExchangeUserId(validator.getExchangeUserId());
        order.setExchangeToyId(validator.getExchangeToyId());
        order.setStatus(validator.getStatus());
        order.setRemark(validator.getRemark());

        return exchangeOrderMapper.insert(order) > 0;
    }

    @Override
    public boolean updateExchangeOrder(ExchangeOrderDO order, CreateOrUpdateExchangeOrder validator) {
        if (order.getRequestUserId() == order.getExchangeUserId()) {
            throw new NotFoundException("不能交换自己的宝贝哦");
        }
        order.setExchangeToyId(validator.getExchangeToyId());
        order.setStatus(validator.getStatus());
        return exchangeOrderMapper.updateById(order) > 0;
    }

    @Override
    public boolean switchExchangeOrderStatus(ExchangeOrderDO order, CreateOrUpdateExchangeOrder validator) {
        order.setStatus(validator.getStatus());
        order.setRemark(validator.getRemark());
        return exchangeOrderMapper.updateById(order) > 0;
    }

    @Override
    public boolean deleteById(Integer id) { return exchangeOrderMapper.deleteById(id) > 0; }
}
