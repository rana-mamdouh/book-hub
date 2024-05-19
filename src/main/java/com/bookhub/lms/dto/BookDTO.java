package com.bookhub.lms.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDTO {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Publication Year is mandatory")
    @Min(value = 1440, message = "Publication Year should not be before 1440")
    @Max(value = 9999, message = "Publication Year should not be in the future")
    private Integer publicationYear;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
    private String isbn;

    @NotBlank(message = "Publisher is mandatory")
    private String publisher;
}
