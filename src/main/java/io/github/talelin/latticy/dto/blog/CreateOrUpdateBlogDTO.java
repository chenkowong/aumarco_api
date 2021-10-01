package io.github.talelin.latticy.dto.blog;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * author chenko@silinsky
 */

@Data
@NoArgsConstructor
public class CreateOrUpdateBlogDTO {

    @Positive(message = "{blog.user_id.positive}")
    private Integer userId;

//    @Positive(message = "{blog.user_id.positive}")
//    private Integer sortId;
    private List<Integer> sortId;

    @NotEmpty(message = "{blog.blog_title.not-empty}")
    @Length(max = 100, message = "{blog.blog_title.length}")
    private String blogTitle;

    @NotEmpty(message = "{blog.blog_cover.not-empty}")

    @Length(max = 500, message = "{blog.blog_cover.length}")
    private String blogCover;
    @NotEmpty(message = "{blog.blog_markdown.not_empty}")
    @Length(max = 50000, message = "{blog.blog_markdown.length}")
    private String blogMarkdown;

    @NotEmpty(message = "{blog.blog_content.not_empty}")
    @Length(max = 50000, message = "{blog.blog_content.length}")
    private String blogContent;

    private Integer blogViews;

    private Integer blogLikeCount;

    private Integer blogCommentCount;
}
