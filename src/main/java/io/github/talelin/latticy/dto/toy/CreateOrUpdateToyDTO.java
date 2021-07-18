package io.github.talelin.latticy.dto.toy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreateOrUpdateToyDTO {

    private String toyNo;

    @Positive(message = "{blog.user_id.positive}")
    private Integer userId;

    @Positive(message = "{blog.user_id.positive}")
    private Integer sortId;

    @Length(max = 100, message = "{blog.blog_title.length}")
    private String toyTitle;

    @NotEmpty(message = "{blog.blog_cover.not-empty}")
    @Length(max = 500, message = "{blog.blog_cover.length}")
    private String toyCover;

    @NotEmpty(message = "{blog.blog_cover.not-empty}")
    @Length(max = 5000, message = "{blog.blog_cover.length}")
    private String toyUrls;

    @NotEmpty(message = "{blog.blog_content.not_empty}")
    @Length(max = 1000, message = "{blog.blog_content.length}")
    private String toyContent;
}
