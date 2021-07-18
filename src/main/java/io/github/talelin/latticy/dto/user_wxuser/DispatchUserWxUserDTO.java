package io.github.talelin.latticy.dto.user_wxuser;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class DispatchUserWxUserDTO {
    @Positive(message = "{user.id.positive}")
    @NotNull(message = "{user.id.not-null}")
    private Integer userId;

    @Positive(message = "{blog.sort_id.positive}")
    @NotNull(message = "{blog.sort_id.not-null}")
    private Integer wxuserId;
}
