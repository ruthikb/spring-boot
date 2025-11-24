package com.xworkz.ecomerce.h2.service;

import com.xworkz.ecomerce.h2.dto.CustomerDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerDto dto);

    List<CustomerDto> findAll();

    void deleteUser(long id);

//    String updateUser(@Valid CustomerDto dto);
}
