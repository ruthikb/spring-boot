package com.xworkz.ecomerce.h2.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CustomerDto {
    private String firstName;
    private  String  lastName;
    private String email;
}
