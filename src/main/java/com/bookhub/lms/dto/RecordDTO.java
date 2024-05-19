package com.bookhub.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RecordDTO {
    private Long id;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;

    @NotNull(message = "Patron ID is mandatory")
    private Long patronId;

    @NotNull(message = "Borrowed Date is mandatory")
    private Date borrowedDate;

    private Date returnDate;
}
