package com.xworkz.ecomerce.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long updateAddressId;

        @NotBlank(message = "Street cannot be blank")
        @Size(max = 100, message = "Street name must be under 100 characters")
        private String street;

        @NotBlank(message = "City cannot be blank")
        @Size(max = 50, message = "City name must be under 50 characters")
        private String city;

        @NotBlank(message = "State cannot be blank")
        @Size(max = 50, message = "State name must be under 50 characters")
        private String state;

        @NotBlank(message = "Postal code is required")
        @Pattern(regexp = "\\d{5,6}", message = "Postal code must be 5 or 6 digits")
        private String postalCode;

        @NotBlank(message = "Country cannot be blank")
        @Size(max = 50, message = "Country name must be under 50 characters")
        private String country;

        @Size(max = 100, message = "Landmark must be under 100 characters")
        private String landmark;

        @NotBlank(message = "AddressDto type is required")
        @Pattern(regexp = "Home|Office|Billing", message = "AddressDto type must be Home, Office, or Billing")
        private String addressType;
}
