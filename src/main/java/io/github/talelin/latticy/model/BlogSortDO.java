package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 博客分类中间件
 * @author Chenko@SilinSky
 */

@TableName("blog_sort")
@Data
public class BlogSortDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer blogId;
    private Integer sortId;
    private String sortName;

    public BlogSortDO() {}

    public BlogSortDO(Integer blogId, Integer sortId) {
        this.blogId = blogId;
        this.sortId = sortId;
    }
}
