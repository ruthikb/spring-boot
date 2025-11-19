package com.xworkz.ecomerce.h2.service.impl;

import com.xworkz.ecomerce.h2.repository.CustomerRepository;
import com.xworkz.ecomerce.h2.dto.CustomerDto;
import com.xworkz.ecomerce.h2.entity.CustomerEntity;
import com.xworkz.ecomerce.h2.mapper.CustomerMapper;
import com.xworkz.ecomerce.h2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;



    @Override
    public CustomerDto save(CustomerDto dto) {
       CustomerEntity customerEntity= CustomerMapper.INSTANCE.dtoToEntity(dto);
       CustomerEntity savedEntity= repo.save(customerEntity);
       return CustomerMapper.INSTANCE.auditLogEntityToAuditLogDto(savedEntity);
    }

    @Override
    public List<CustomerDto> findAll() {
        return repo.findAll().stream()
                .map(entity -> CustomerMapper.INSTANCE.auditLogEntityToAuditLogDto(entity))
                .collect(Collectors.toList());
    }
}
