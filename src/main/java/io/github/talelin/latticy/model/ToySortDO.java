package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("itoy_toy_sort")
@Data
public class ToySortDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer toyId;
    private Integer sortId;
}
