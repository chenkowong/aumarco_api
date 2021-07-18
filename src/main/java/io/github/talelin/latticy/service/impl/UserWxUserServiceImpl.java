package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.mapper.UserWxUserMapper;
import io.github.talelin.latticy.model.UserWxUserDO;
import io.github.talelin.latticy.service.UserWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWxUserServiceImpl implements UserWxUserService {

    @Autowired
    private UserWxUserMapper userWxUserMapper;

    @Override
    public boolean dispatchUserWxUser(UserWxUserDO userWxUserDO) {
        return userWxUserMapper.insert(userWxUserDO) > 0;
    }

    @Override
    public UserWxUserDO selectUserIdByWxUserId(Integer wxuserId) {
        UserWxUserDO userWxUser = userWxUserMapper.selectUserIdByWxUserId(wxuserId);
        return userWxUser;
    }
}
