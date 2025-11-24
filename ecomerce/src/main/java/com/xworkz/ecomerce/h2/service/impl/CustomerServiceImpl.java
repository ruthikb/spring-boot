package com.xworkz.ecomerce.h2.service.impl;

import com.xworkz.ecomerce.h2.repository.CustomerRepository;
import com.xworkz.ecomerce.h2.dto.CustomerDto;
import com.xworkz.ecomerce.h2.entity.CustomerEntity;
import com.xworkz.ecomerce.h2.mapper.CustomerMapper;

import com.xworkz.ecomerce.h2.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Cacheable("products")
    public List<CustomerDto> findAll() {
        return repo.findAll().stream()
                .map(entity -> CustomerMapper.INSTANCE.auditLogEntityToAuditLogDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "products" , allEntries = true)
    public void deleteUser(long id) {
        repo.deleteById(id);
    }




}
