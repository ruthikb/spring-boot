package com.xworkz.ecomerce.mysql.controller;

import com.xworkz.ecomerce.mysql.dto.UserDto;
import com.xworkz.ecomerce.mysql.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        System.err.println(userDto);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + result.getAllErrors());
        }
        System.out.println(userDto);
        String message = userService.registerUser(userDto);

        if (message.equals("User already exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else if (message.equals("User registered successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
    @GetMapping
    public  ResponseEntity<UserDto> getUser(@PathVariable("id") int id){
        UserDto user = userService.getUserById(id);
        System.err.println(user);
        if (user == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
    @PutMapping
    public  ResponseEntity<UserDto>updateUser(@PathVariable("id")int id,@RequestBody UserDto userDto){
        UserDto userDto1=userService.updateUser(id,userDto );
        if (userDto1==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDto1);
    }
    @DeleteMapping
    public  ResponseEntity<UserDto> deleteUser(@PathVariable("id")int id){
      boolean delete=  userService.deleteUser(id);
      if (!delete){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.noContent().build();
    }





}
