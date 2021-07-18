package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.dto.wxuser.RegisterWxUserDTO;
import io.github.talelin.latticy.mapper.WxUserMapper;
import io.github.talelin.latticy.model.WxUserDO;
import io.github.talelin.latticy.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUserDO> implements WxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Override
    public WxUserDO selectByOpenId(String openId) {
        WxUserDO wxuser = wxUserMapper.selectByOpenId(openId);
        return wxuser;
    }

    @Override
    public WxUserDO createWxUser(RegisterWxUserDTO validator) {
        // TODO 请求微信API并返回结果注入wx_user
        // authorization.wechatAuth()
        WxUserDO wx_user = new WxUserDO();
        wx_user.setOpenId(validator.getOpenId());
        wx_user.setSkey(validator.getSkey());
        wx_user.setSessionKey(validator.getSessionKey());
        wx_user.setCity(validator.getCity());
        wx_user.setProvince(validator.getProvince());
        wx_user.setCountry(validator.getCountry());
        wx_user.setAvatarUrl(validator.getAvatarUrl());
        wx_user.setGender(validator.getGender());
        wx_user.setNickName(validator.getNickName());
        this.baseMapper.insert(wx_user);
        return wx_user;
    }
}
