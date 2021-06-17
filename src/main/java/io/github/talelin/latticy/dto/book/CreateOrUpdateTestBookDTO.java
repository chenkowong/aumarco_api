package io.github.talelin.latticy.dto.book;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateOrUpdateTestBookDTO {

    @NotEmpty(message = "{book.title.not-empty}")
    @Size(max = 50, message = "{book.title.size}")
    private String title;

    @NotEmpty(message = "{book.author.not-empty}")
    @Size(max = 50, message = "{book.author.size}")
    private String author;

    @NotEmpty(message = "{book.summary.not-empty}")
    @Size(max = 50, message = "{book.summary.size}")
    private String summary;

    @Size(max = 100, message = "{book.image.size}")
    private String image;
}
