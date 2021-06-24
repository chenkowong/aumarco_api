package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("visitor")
@Data
public class VisitorDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String cip;
    private String cid;
    private String cname;
    private Integer count;
}
