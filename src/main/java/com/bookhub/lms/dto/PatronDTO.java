package com.bookhub.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatronDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Contact Information is mandatory")
    private String contactInformation;

    @NotBlank(message = "Address is mandatory")
    private String address;
}
