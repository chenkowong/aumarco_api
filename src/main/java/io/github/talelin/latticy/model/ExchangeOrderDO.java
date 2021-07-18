package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 交换订单
 * @author Chenko@SilinSky
 */
@TableName("itoy_exchange_order")
@Data
public class ExchangeOrderDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String orderNo;
    private Integer requestToyId;
    private Integer requestUserId;
    private Integer exchangeUserId;
    private Integer exchangeToyId;
    private Integer status;
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonIgnore
    @TableLogic
    private Date deleteTime;
}
