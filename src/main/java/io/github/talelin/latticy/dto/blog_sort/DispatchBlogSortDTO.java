package io.github.talelin.latticy.dto.blog_sort;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * author Chenko@silinsky
 */

@Data
@NoArgsConstructor
public class DispatchBlogSortDTO {
    @Positive(message = "{blog.id.positive}")
    @NotNull(message = "{blog.id.not-null}")
    private Integer blogId;

    @Positive(message = "{blog.sort_id.positive}")
    @NotNull(message = "{blog.sort_id.not-null}")
    private Integer sortId;
}
