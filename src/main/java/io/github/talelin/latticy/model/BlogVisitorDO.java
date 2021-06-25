package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("blog_visitor")
@Data
public class BlogVisitorDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer blogId;
    private Integer visitorId;
    private Integer count;
}
