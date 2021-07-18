package io.github.talelin.latticy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.talelin.latticy.model.ExchangeOrderDO;
import io.github.talelin.latticy.model.UserDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Map;

@Data
public class ExchangeOrderVO {

    private Integer id;
    private String orderNo;
    private UserDO requestUser;
    private ToyInfoVO requestToy;
    private UserDO exchangeUser;
    private ToyInfoVO exchangeToy;
    private Integer status;
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public ExchangeOrderVO(
            ExchangeOrderDO order,
            UserDO requestUser,
            ToyInfoVO requestToy,
            UserDO exchangeUser,
            ToyInfoVO exchangeToy
    ) {
        BeanUtils.copyProperties(order, this);
        this.requestUser = requestUser;
        this.requestToy = requestToy;
        this.exchangeUser = exchangeUser;
        this.exchangeToy = exchangeToy;
    }
}
