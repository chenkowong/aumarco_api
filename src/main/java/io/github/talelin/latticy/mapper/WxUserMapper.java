package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.WxUserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserMapper extends BaseMapper<WxUserDO> {
    WxUserDO selectByOpenId(String openId);
}
