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

    @NotEmpty(message = "{book.title.not-empty}")
    @Length(max = 50, message = "{book.title.length}")
    private String sortName;

    @NotEmpty(message = "{book.author.not-empty}")
    @Length(max = 15, message = "{book.author.length}")
    private String sortAlias;

    @NotEmpty(message = "{book.summary.not-empty}")
    @Length(max = 255, message = "{book.summary.length}")
    private String sortDescription;

    private Integer parentSortId;
}
