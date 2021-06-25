package io.github.talelin.latticy.dto.blog_sort;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RemoveBlogSortDTO {

    @Positive(message = "{blog.id.positive}")
    @NotNull(message = "{blog.id.not-null}")
    private Integer blogId;

    private Integer sortId;
}
