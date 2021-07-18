package io.github.talelin.latticy.controller.cms;

import com.alibaba.fastjson.JSONObject;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.bo.FileBO;
import io.github.talelin.latticy.module.wechat.WechatUtil;
import io.github.talelin.latticy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @author pedro@TaleLin
 * @author Juzi@TaleLin
 */
@RestController
@RequestMapping("/cms/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param multipartHttpServletRequest 携带文件的 request
     * @return 文件信息
     */
    @PostMapping
    @LoginRequired
    public List<FileBO> upload(MultipartHttpServletRequest multipartHttpServletRequest) {
        MultiValueMap<String, MultipartFile> fileMap =
                multipartHttpServletRequest.getMultiFileMap();
        return fileService.upload(fileMap);
    }

    @PostMapping("/wx_file")
    @LoginRequired
    public List<FileBO> wx_upload(MultipartHttpServletRequest multipartHttpServletRequest) {
        JSONObject getAccessToken = WechatUtil.getAccessToken();
        String access_token = getAccessToken.getString("access_token");
        String err_msg = getAccessToken.getString("errmsg");
        if (access_token == null) {
            throw new NotFoundException("获取微信凭证接口失败: " + err_msg);
        }
        MultiValueMap<String, MultipartFile> fileMap =
                multipartHttpServletRequest.getMultiFileMap();
        fileMap.keySet().forEach(key -> fileMap.get(key).forEach(file -> {
            JSONObject res = WechatUtil.imgSecCheck(access_token, file);
            String err_code = res.getString("errcode");
            if (Integer.valueOf(err_code) != 0) {
                throw new NotFoundException("图片内容存在潜在风险，请重新选择");
            }
        }));
        return fileService.upload(fileMap);
    }
}
