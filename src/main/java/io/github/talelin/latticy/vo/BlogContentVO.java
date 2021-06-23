package io.github.talelin.latticy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.SortDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogContentVO {

    private Integer id;
    private Integer sortId;
    private String sortName;
    private String blogTitle;
    private String blogCover;
    private String blogMarkdown;
    private String blogContent;
    private Integer blogViews;
    private Integer blogLikeCount;
    private Integer blogCommentCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public BlogContentVO(BlogDO blog, SortDO sort) {
        BeanUtils.copyProperties(blog, this);
        this.sortId = sort.getId();
        this.sortName = sort.getSortName();
    }
}
