package io.github.talelin.latticy.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreateOrUpdateExchangeOrder {

    private String orderNo;

    @Positive(message = "{blog.user_id.positive}")
    private Integer requestToyId;

    @Positive(message = "{blog.user_id.positive}")
    private Integer requestUserId;

//    @Positive(message = "{blog.user_id.positive}")
    private Integer exchangeUserId;

    @Positive(message = "{blog.user_id.positive}")
    private Integer exchangeToyId;

//    @Positive(message = "{blog.user_id.positive}")
    private Integer status;

    @Length(max = 255, message = "{blog.blog_title.length}")
    private String remark;
}
