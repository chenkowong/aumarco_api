package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.dto.visitor.CreateOrUpdateVisitorDTO;
import io.github.talelin.latticy.mapper.VisitorMapper;
import io.github.talelin.latticy.model.VisitorDO;
import io.github.talelin.latticy.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, VisitorDO> implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public VisitorDO selectVisitorByCip(String cip) {
        VisitorDO visitor = visitorMapper.selectVisitorByCip(cip);
        return visitor;
    }

    @Override
    public VisitorDO selectById(Integer id) {
        VisitorDO visitor = visitorMapper.selectById(id);
        return visitor;
    }

    @Override
    public boolean createVisitor(CreateOrUpdateVisitorDTO validator) {
        VisitorDO visitor = new VisitorDO();
        visitor.setCip(validator.getCip());
        visitor.setCid(validator.getCid());
        visitor.setCname(validator.getCname());
        visitor.setCount(validator.getCount());
        return visitorMapper.insert(visitor) > 0;
    }

    @Override
    public boolean updateVisitor(VisitorDO visitor, CreateOrUpdateVisitorDTO validator) {
        visitor.setCount(validator.getCount());
        return visitorMapper.updateById(visitor) > 0;
    }
}
