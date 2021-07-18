package io.github.talelin.latticy.dto.toy_sort;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RemoveToySortDTO {

    @Positive(message = "{blog.id.positive}")
    @NotNull(message = "{blog.id.not-null}")
    private Integer toyId;

    private Integer sortId;
}
