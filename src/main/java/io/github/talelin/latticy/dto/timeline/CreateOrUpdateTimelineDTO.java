package io.github.talelin.latticy.dto.timeline;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CreateOrUpdateTimelineDTO {

    @NotEmpty(message = "{blog.blog_title.not-empty}")
    @Length(max = 255, message = "{blog.blog_title.length}")
    private String title;

    private String date;

    private String path;

    private Integer parentId;

    private Integer radio;
}
