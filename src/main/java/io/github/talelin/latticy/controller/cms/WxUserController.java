package io.github.talelin.latticy.controller.cms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.wxuser.RegisterWxUserDTO;
import io.github.talelin.latticy.mapper.WxUserMapper;
import io.github.talelin.latticy.model.WxUserDO;
import io.github.talelin.latticy.module.wechat.WechatUtil;
import io.github.talelin.latticy.service.WxUserService;
import io.github.talelin.latticy.vo.CreatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.UUID;

@RestController
@RequestMapping("/cms/wxlogin")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;


    @PostMapping("")
    public CreatedVO wxlogin(
            @RequestParam(name = "jscode", required = false) String jscode,
            // @RequestParam(name = "rawData", required = false)  String rawData,
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "encrypteData", required = false) String encrypteData,
            @RequestParam(name = "iv", required = false) String iv,
            @RequestBody @Validated RegisterWxUserDTO validator
    ) {
        JSONObject res = WechatUtil.getSessionKeyOrOpenId(jscode);
        String openid = res.getString("openid");
        String sessionKey = res.getString("session_key");
        WxUserDO wx_user = wxUserService.selectByOpenId(openid);
        if (wx_user == null) {
            RegisterWxUserDTO new_wx_user = new RegisterWxUserDTO();
            new_wx_user.setOpenId(openid);
            new_wx_user.setSkey(UUID.randomUUID().toString());
            new_wx_user.setSessionKey(sessionKey);
            new_wx_user.setCity(validator.getCity());
            new_wx_user.setProvince(validator.getProvince());
            new_wx_user.setCountry(validator.getCountry());
            new_wx_user.setAvatarUrl(validator.getAvatarUrl());
            new_wx_user.setGender(validator.getGender());
            new_wx_user.setNickName(validator.getNickName());
            wxUserService.createWxUser(new_wx_user);
            return new CreatedVO(200, "欢迎新用户登录");
        }
        return new CreatedVO(200, "欢迎回来");
    }
}
