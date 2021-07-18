package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("itoy_toy_user")
@Data
public class ToyUserDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer toyId;
    private Integer userId;
    private Integer status;
    private String remark;
}
