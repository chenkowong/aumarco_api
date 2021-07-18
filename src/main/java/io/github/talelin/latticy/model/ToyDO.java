package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@TableName("itoy_toy")
@Data
public class ToyDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String toyNo;
    private String toyTitle;
    private String toyCover;
    private String toyUrls;
    private String toyContent;
    private Integer toyAmount;
    private Integer toyAliasAmount;
    private Integer toyViews;
    private Integer toyLikeCount;
    private Integer toyCommentCount;
    private Integer toyExchangeCount;
    private Integer toyNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonIgnore
    @TableLogic
    private Date deleteTime;
}
