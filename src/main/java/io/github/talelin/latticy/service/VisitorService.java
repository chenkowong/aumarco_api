package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.visitor.CreateOrUpdateVisitorDTO;
import io.github.talelin.latticy.model.VisitorDO;

public interface VisitorService {

    VisitorDO selectVisitorByCip(String cip);

    VisitorDO selectById(Integer id);

    boolean createVisitor(CreateOrUpdateVisitorDTO validator);

    boolean updateVisitor(VisitorDO visitor, CreateOrUpdateVisitorDTO validator);
}
