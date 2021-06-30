package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@TableName("wxuser")
@Data
public class WxUserDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String openId;
    private String skey;


    private String sessionKey;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private Integer gender;
    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonIgnore
    @TableLogic
    private Date deleteTime;
}
