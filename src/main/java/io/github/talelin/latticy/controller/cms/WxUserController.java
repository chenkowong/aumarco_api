package io.github.talelin.latticy.controller.cms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.token.DoubleJWT;
import io.github.talelin.core.token.Tokens;
import io.github.talelin.latticy.dto.user.RegisterDTO;
import io.github.talelin.latticy.dto.wxuser.RegisterWxUserDTO;
import io.github.talelin.latticy.mapper.WxUserMapper;
import io.github.talelin.latticy.model.UserWxUserDO;
import io.github.talelin.latticy.model.WxUserDO;
import io.github.talelin.latticy.module.wechat.WechatUtil;
import io.github.talelin.latticy.service.UserService;
import io.github.talelin.latticy.service.UserWxUserService;
import io.github.talelin.latticy.service.WxUserService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.model.UserDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cms/wxlogin")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserWxUserService userWxUserService;

    @Autowired
    private DoubleJWT jwt;


    @PostMapping("")
    public Tokens wxlogin(
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
            WxUserDO wxUserDO =  wxUserService.createWxUser(new_wx_user);
            /**
             * ????????????User
             * userService.createUser(validator)
             * @param username: "wx" + ?????????     // String
             * @param password: open_id          // String
             * @param confirm_password: open_id  // String
             * @param group_ids???[2]             // List<Integer> ???????????????
             */

            List<Integer> groupIds = new ArrayList<>();
            groupIds.add(2);

            RegisterDTO new_user = new RegisterDTO();
            new_user.setUsername('u' + String.valueOf(new Date().getTime()));
            new_user.setPassword(openid);
            new_user.setNickname(validator.getNickName());
            new_user.setAvatar(validator.getAvatarUrl());
            new_user.setConfirmPassword(openid);
            new_user.setGroupIds(groupIds);
            UserDO userDO = userService.createUser(new_user);

            /**
             * ??????????????????????????????
             */
            UserWxUserDO userWxUserDO = new UserWxUserDO();
            userWxUserDO.setUserId(userDO.getId());
            userWxUserDO.setWxuserId(wxUserDO.getId());
            userWxUserService.dispatchUserWxUser(userWxUserDO);

            return jwt.generateTokens(userDO.getId());
        }
        // TODO ??????????????????token
        UserWxUserDO userWxUser = userWxUserService.selectUserIdByWxUserId(wx_user.getId());
        if (userWxUser == null) {
            throw new NotFoundException("??????openId??????????????????????????????");
        }
        UserDO user = userService.getById(userWxUser.getUserId());
        if (user == null) {
            throw new NotFoundException("??????????????????????????????");
        }
        return jwt.generateTokens(user.getId());
    }

    @GetMapping("")
    public Tokens wxAuth(@RequestParam(name = "jscode", required = false) String jscode) {
        JSONObject res = WechatUtil.getSessionKeyOrOpenId(jscode);
        String openid = res.getString("openid");
        WxUserDO wx_user = wxUserService.selectByOpenId(openid);
        if (wx_user == null) {
            throw new NotFoundException("????????????????????????");
        }
        UserWxUserDO userWxUser = userWxUserService.selectUserIdByWxUserId(wx_user.getId());
        if (userWxUser == null) {
            throw new NotFoundException("??????openId??????????????????????????????");
        }
        UserDO user = userService.getById(userWxUser.getUserId());
        if (user == null) {
            throw new NotFoundException("??????????????????????????????");
        }
        return jwt.generateTokens(user.getId());
    }
}
