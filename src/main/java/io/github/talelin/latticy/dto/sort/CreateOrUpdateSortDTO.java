package io.github.talelin.latticy.dto.sort;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * author chenko@silinsky
 */

@Data
@NoArgsConstructor
public class CreateOrUpdateSortDTO {

    @NotEmpty(message = "{sort.sort_name.not-empty}")
    @Length(max = 50, message = "{sort.sort_name.length}")
    private String sortName;

    @NotEmpty(message = "{sort.sort_alias.not-empty}")
    @Length(max = 15, message = "{sort.sort_alias.length}")
    private String sortAlias;

    @NotEmpty(message = "{sort.sort_description.not_empty}")
    @Length(max = 255, message = "{sort.sort_description.length}")
    private String sortDescription;

    private Integer parentSortId;
}
