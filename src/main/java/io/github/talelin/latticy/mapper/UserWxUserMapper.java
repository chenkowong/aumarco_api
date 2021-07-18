package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.UserWxUserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWxUserMapper extends BaseMapper<UserWxUserDO> {

    UserWxUserDO selectUserIdByWxUserId(Integer wxuserId);

}
