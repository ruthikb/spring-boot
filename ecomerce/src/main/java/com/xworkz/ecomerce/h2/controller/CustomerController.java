package com.xworkz.ecomerce.h2.controller;

import com.xworkz.ecomerce.h2.dto.CustomerDto;
import com.xworkz.ecomerce.h2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {
        System.err.println("before method calling"+dto);
        return ResponseEntity.ok(service.save(dto));

    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
