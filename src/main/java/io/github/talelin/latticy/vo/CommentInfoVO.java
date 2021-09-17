package io.github.talelin.latticy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.CommentDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfoVO {

    private Integer id;
    private String blogTitle;
    private Integer blogId;
    private String name;
    private String email;
    private String website;
    private String comment;
    private String reply;
    private Integer parentId;
    private Integer commentLikeCount;
    private Integer isOwner;
    private List<CommentDO> children;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public CommentInfoVO(CommentDO comment, BlogDO blog, List<CommentDO> children) {
        BeanUtils.copyProperties(comment, this);
        if (blog != null) this.blogTitle = blog.getBlogTitle();
        else this.blogTitle = null;
        if (children.isEmpty()) this.children = null;
        else this.children = children;
    }
}
