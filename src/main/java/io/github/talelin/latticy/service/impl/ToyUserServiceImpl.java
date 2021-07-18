package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.toy_user.DispatchToyUserDTO;
import io.github.talelin.latticy.dto.toy_user.RemoveToyUserDTO;
import io.github.talelin.latticy.mapper.ToyUserMapper;
import io.github.talelin.latticy.model.ToyUserDO;
import io.github.talelin.latticy.service.ToyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToyUserServiceImpl implements ToyUserService {

    @Autowired
    private ToyUserMapper toyUserMapper;

    @Override
    public IPage<ToyUserDO> selectPageByUserId(Integer page, Integer count, Integer userId, Integer status) {
        Page<ToyUserDO> paper = new Page<>(page, count);
        IPage<ToyUserDO> iPage = toyUserMapper.selectPageByUserId(paper, userId, status);
        return iPage;
    }

    @Override
    public boolean dispatchToyUser(ToyUserDO validator) {
        return toyUserMapper.insert(validator) > 0;
    }

    @Override
    public ToyUserDO selectById(Integer id) {
        ToyUserDO toyUser = toyUserMapper.selectById(id);
        return toyUser;
    }

    @Override
    public ToyUserDO selectByToyId(Integer toyId) {
        ToyUserDO toyUser = toyUserMapper.selectByToyId(toyId);
        return toyUser;
    }

    @Override
    public boolean updateToyUser(ToyUserDO toyUser, DispatchToyUserDTO dto) {
        toyUser.setStatus(dto.getStatus());
        toyUser.setRemark(dto.getRemark());
        return toyUserMapper.updateById(toyUser) > 0;
    }

    @Override
    public boolean removeToyUser(RemoveToyUserDTO dto) {
        return toyUserMapper.deleteBatchByToyIdAndUserId(dto.getToyId(), dto.getUserId()) > 0;
    }
}
