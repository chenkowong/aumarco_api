package io.github.talelin.latticy.service;


import io.github.talelin.latticy.model.UserWxUserDO;

public interface UserWxUserService {

    boolean dispatchUserWxUser(UserWxUserDO validator);

    UserWxUserDO selectUserIdByWxUserId(Integer wxuserId);

}
