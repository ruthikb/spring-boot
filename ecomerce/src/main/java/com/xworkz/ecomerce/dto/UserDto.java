package com.xworkz.ecomerce.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Data
public class UserDto {
    private int updateId;

    @Schema(description = "user name")
    @NotBlank(message = "Customer name is required")
    private String name;

    @Schema(description = "user email")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "user phone Number")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Schema(description = "user address")
    @Valid
    private List<AddressDto> addressDtos;
}