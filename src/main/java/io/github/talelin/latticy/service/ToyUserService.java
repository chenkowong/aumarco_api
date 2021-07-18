package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.dto.toy_user.DispatchToyUserDTO;
import io.github.talelin.latticy.dto.toy_user.RemoveToyUserDTO;
import io.github.talelin.latticy.model.ToyUserDO;

public interface ToyUserService {
    IPage<ToyUserDO> selectPageByUserId(Integer page, Integer count, Integer userId, Integer status);

    boolean dispatchToyUser(ToyUserDO validator);

    ToyUserDO selectById(Integer id);

    ToyUserDO selectByToyId(Integer toyId);

    boolean updateToyUser(ToyUserDO toyUser, DispatchToyUserDTO dto);

    boolean removeToyUser(RemoveToyUserDTO dto);
}
