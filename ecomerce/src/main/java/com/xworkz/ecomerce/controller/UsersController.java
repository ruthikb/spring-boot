package com.xworkz.ecomerce.controller;

import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ListOfUsers")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("")
        public ResponseEntity<List<UserDto>> createListOfUsers(@RequestBody List < UserDto> userdtos) {
            List<UserDto> listOfUsers = userService.createUsers(userdtos);
            return new ResponseEntity<>(listOfUsers, HttpStatus.OK);

    }
}
