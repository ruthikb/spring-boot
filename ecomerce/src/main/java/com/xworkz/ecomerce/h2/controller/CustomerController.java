package com.xworkz.ecomerce.h2.controller;

import com.xworkz.ecomerce.h2.dto.CustomerDto;
import com.xworkz.ecomerce.h2.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    @DeleteMapping
    public ResponseEntity<String> delete(long id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("deleted");
    }
//    @PutMapping
//    public ResponseEntity<String> updateUser(@RequestBody @Valid CustomerDto dto , BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
//        }
//        String result = service.updateUser(dto);
//        if (result.equals("no id")){
//            return ResponseEntity.status(HttpStatus.OK).body("wrong id");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body("updated");
//    }

}
