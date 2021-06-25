package io.github.talelin.latticy.vo;

import io.github.talelin.latticy.model.BlogVisitorDO;
import io.github.talelin.latticy.model.VisitorDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogVisitorInfoVO {

    private Integer id;
    private String cip;
    private String cid;
    private String cname;
    private Integer blogViews;

    public BlogVisitorInfoVO(VisitorDO visitor, BlogVisitorDO blogVisitor) {
        BeanUtils.copyProperties(visitor, this);
        this.blogViews = blogVisitor.getCount();
    }
}
