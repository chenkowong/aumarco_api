package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.visitor.CreateOrUpdateVisitorDTO;
import io.github.talelin.latticy.model.VisitorDO;

public interface VisitorService {

    IPage<VisitorDO> selectPageByKeyWord(Integer page, Integer count, String keyWord);

    VisitorDO selectVisitorByCip(String cip);

    VisitorDO selectById(Integer id);

    boolean createVisitor(CreateOrUpdateVisitorDTO validator);

    boolean updateVisitor(VisitorDO visitor, CreateOrUpdateVisitorDTO validator);
}
