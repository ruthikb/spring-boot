package com.xworkz.ecomerce.h2.service;

import com.xworkz.ecomerce.h2.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerDto dto);

    List<CustomerDto> findAll();
}
