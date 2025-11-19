package com.xworkz.ecomerce.mysql.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;
    private String firstName;

    private String lastName;
    private String email;

    private long phoneNo;

    private String address;
    private String password;


}
