package io.github.talelin.latticy.dto.blog;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreateOrUpdateCommentDTO {

    @Positive(message = "没有获取到相关博客ID")
    private Integer blogId;

    @Length(max = 100, message = "昵称过长")
    private String name;
    private String email;
    private String website;

    @NotEmpty(message = "你还没有填写评论")
    @Length(max = 5000, message = "评论字数超过限制")
    private String comment;
    private String reply;

    private Integer parentId;
    private Integer commentLikeCount;

    private Integer isOwner;
}
