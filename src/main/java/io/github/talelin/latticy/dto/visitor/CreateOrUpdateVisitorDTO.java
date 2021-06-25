package io.github.talelin.latticy.dto.visitor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreateOrUpdateVisitorDTO {

    @NotEmpty(message = "{visitor.cip.not-empty}")
    @Length(max = 50, message = "{visitor.cip.length}")
    private String cip;

    @NotEmpty(message = "{visitor.cid.not-empty}")
    @Length(max = 50, message = "{visitor.cid.length}")
    private String cid;

    @NotEmpty(message = "{visitor.cname.not-empty}")
    @Length(max = 100, message = "{visitor.cname.length}")
    private String cname;

    @Positive(message = "{group.id.positive}")
    private Integer count;
}
