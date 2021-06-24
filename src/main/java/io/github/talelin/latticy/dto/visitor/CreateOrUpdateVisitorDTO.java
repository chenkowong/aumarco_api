package io.github.talelin.latticy.dto.visitor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreateOrUpdateVisitorDTO {

    @NotEmpty(message = "{sort.sort_name.not-empty}")
    @Length(max = 50, message = "{sort.sort_name.length}")
    private String cip;

    @NotEmpty(message = "{sort.sort_name.not-empty}")
    @Length(max = 50, message = "{sort.sort_name.length}")
    private String cid;

    @NotEmpty(message = "{sort.sort_name.not-empty}")
    @Length(max = 100, message = "{sort.sort_name.length}")
    private String cname;

    @Positive(message = "{group.id.positive}")
    private Integer count;
}
