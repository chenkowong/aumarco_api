package io.github.talelin.latticy.module.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.talelin.latticy.common.util.HttpClientUtil;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WechatUtil {

    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        // https://mp.weixin.qq.com/wxopen/devprofile?action=get_profile&token=164113089&lang=zh_CN
        // 小程序appId
        requestUrlParam.put("appid", "wxb005ca6271aa48d7");
        // 小程序secret
        requestUrlParam.put("secret", "fcacac6b9ba2706c33f5c2414a80ff2a");
        // 小程序端返回的code
        requestUrlParam.put("js_code", code);
        // 默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        // 发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject getAccessToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> requestUrlParam = new HashMap<>();
        // 默认参数
        requestUrlParam.put("grant_type", "client_credential");
        // 小程序appId
        requestUrlParam.put("appid", "wxb005ca6271aa48d7");
        // 小程序secret
        requestUrlParam.put("secret", "fcacac6b9ba2706c33f5c2414a80ff2a");
        // 发送请求读取调用微信接口获取AccessToken
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static JSONObject imgSecCheck(String token, MultipartFile file) {
        String requestUrl = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + token;
        Map<String, String> requestUrlParam = new HashMap<>();
        //access_token
        // requestUrlParam.put("access_token", token);
        // TODO 图片格式 FormData
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPostFormData(requestUrl, requestUrlParam, file));
        return jsonObject;
    }

    public static JSONObject msgSecCheck(String token, String content) {
        String requestUrl = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + token;
        Map<String, String> requestUrlParam = new HashMap<>();

        Map<String, String> requestUrlBody = new HashMap<>();
        // 检测内容content
        requestUrlBody.put("content", content);
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPostBody(requestUrl, requestUrlParam, requestUrlBody));
        return jsonObject;
    }
}
