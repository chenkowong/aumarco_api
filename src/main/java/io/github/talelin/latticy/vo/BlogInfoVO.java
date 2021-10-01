package io.github.talelin.latticy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.talelin.latticy.model.BlogDO;
import io.github.talelin.latticy.model.BlogSortDO;
import io.github.talelin.latticy.model.SortDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfoVO {

    private Integer id;
    private List<BlogSortDO> sort;
    private String blogTitle;
    private String blogCover;
    private Integer blogViews;
    private Integer blogLikeCount;
    private Integer blogCommentCount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    public BlogInfoVO(BlogDO blog, List<BlogSortDO> sort) {
        BeanUtils.copyProperties(blog, this);
        if (sort != null && !sort.isEmpty()) {
            this.sort = sort;
        } else {
            this.sort = new ArrayList<>();
        }
    }
}
