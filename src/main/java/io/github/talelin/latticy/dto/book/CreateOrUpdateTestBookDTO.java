package io.github.talelin.latticy.dto.book;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateTestBookDTO {

    @NotEmpty(message = "必须传入图书名")
    @Size(max = 50, message = "图书名不能超过50个字")
    private String title;

    @NotEmpty(message = "必须传入图书作者")
    @Size(max = 50, message = "图书作者名不能超过30字符")
    private String author;

    @NotEmpty(message = "必须传入图书综述")
    @Size(max = 50, message = "图书综述不能超过1000字符")
    private String summary;

    @Size(max = 100, message = "图书插图的url长度必须在0-100之间")
    private String image;
}
