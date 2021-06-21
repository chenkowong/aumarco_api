package io.github.talelin.latticy.vo;

import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.SortDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfoVO {

    private Integer id;
    private String sortName;
    private String blogTitle;
    private String blogCover;
    private Integer blogViews;
    private Integer blogLikeCount;
    private Integer blogCommentCount;

    public BlogInfoVO(BlogDO blog, SortDO sort) {
        BeanUtils.copyProperties(blog, this);
        this.sortName = sort.getSortName();
    }
}
