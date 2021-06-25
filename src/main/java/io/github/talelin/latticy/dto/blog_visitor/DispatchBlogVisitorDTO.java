package io.github.talelin.latticy.dto.blog_visitor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class DispatchBlogVisitorDTO {
    @Positive(message = "{blog.id.positive}")
    @NotNull(message = "{blog.id.not-null}")
    private Integer blogId;

    @Positive(message = "{visitor.id.positive}")
    @NotNull(message = "{visitor.id.not-null}")
    private Integer visitorId;

    @Positive(message = "{visitor.id.positive}")
    @NotNull(message = "{visitor.id.not-null}")
    private Integer count;
}
