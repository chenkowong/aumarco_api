package io.github.talelin.latticy.dto.blog;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * author chenko@silinsky
 */

@Data
@NoArgsConstructor
public class CreateOrUpdateBlogDTO {

    @Positive(message = "{group.id.positive}")
    private Integer userId;

    @Positive(message = "{group.id.positive}")
    private Integer sortId;

    @NotEmpty(message = "{sort.sort_name.not-empty}")
    @Length(max = 100, message = "{sort.sort_name.length}")
    private String blogTitle;

    @NotEmpty(message = "{sort.sort_alias.not-empty}")
    @Length(max = 500, message = "{sort.sort_alias.length}")
    private String blogCover;

    @NotEmpty(message = "{sort.sort_description.not_empty}")
    @Length(max = 50000, message = "{sort.sort_description.length}")
    private String blogMarkdown;

    @NotEmpty(message = "{sort.sort_description.not_empty}")
    @Length(max = 50000, message = "{sort.sort_description.length}")
    private String blogContent;

    private Integer blogViews;

    private Integer blogLikeCount;

    private Integer blogCommentCount;
}
