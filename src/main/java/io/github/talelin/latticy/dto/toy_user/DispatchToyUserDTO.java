package io.github.talelin.latticy.dto.toy_user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class DispatchToyUserDTO {
    private Integer toyId;

    private Integer userId;

    @Positive(message = "{blog.id.positive}")
    @NotNull(message = "{blog.id.not-null}")
    private Integer status;

    private String remark;
}
