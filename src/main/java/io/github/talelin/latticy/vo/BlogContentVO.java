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

    private Integer prevBlogId; // 获取前一篇博客的id
    private Integer nextBlogId; // 获取后一篇博客的id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public BlogContentVO(BlogDO blog, BlogDO prevBlog, BlogDO nextBlog, SortDO sort) {
        BeanUtils.copyProperties(blog, this);
        this.sortId = sort.getId();
        if (prevBlog == null) {
            this.prevBlogId = -1;
        } else {
            this.prevBlogId = prevBlog.getId();
        }
        if (nextBlog == null) {
            this.nextBlogId = -1;
        } else {
            this.nextBlogId = nextBlog.getId();
        }
        this.sortName = sort.getSortName();
    }
}
