package io.github.talelin.latticy.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.dto.wxuser.RegisterWxUserDTO;
import io.github.talelin.latticy.model.WxUserDO;

public interface WxUserService extends IService<WxUserDO> {

    WxUserDO selectByOpenId(String oepnId);

    boolean createWxUser(RegisterWxUserDTO validator);
}
