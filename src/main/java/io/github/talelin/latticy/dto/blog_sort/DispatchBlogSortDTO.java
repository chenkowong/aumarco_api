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
    @Positive(message = "{group.id.positive}")
    @NotNull(message = "{group.id.not-null}")
    private Integer blogId;

    @Positive(message = "{group.id.positive}")
    @NotNull(message = "{group.id.not-null}")
    private Integer sortId;
}
