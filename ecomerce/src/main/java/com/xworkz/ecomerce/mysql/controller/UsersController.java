package com.xworkz.ecomerce.mysql.controller;

import com.xworkz.ecomerce.mysql.dto.UserDto;
import com.xworkz.ecomerce.mysql.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ListOfUsers")
@AllArgsConstructor
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping()
        public ResponseEntity<String > createListOfUsers(@RequestBody List<UserDto> userdtos) {
        if (userdtos == null ||userdtos.isEmpty()){
            return ResponseEntity.badRequest().body("User list is empty");
        }
            boolean isUsersSaved = userService.createUsers(userdtos);
            if (!isUsersSaved){
                return ResponseEntity.badRequest().body("Failed to create users");
            }else return ResponseEntity.ok("Users created successfully");

    }

    @PutMapping()
    public ResponseEntity<String> updateListOfUsers(@RequestBody List<UserDto> userdtos) {
        if (userdtos == null ||userdtos.isEmpty()){
            return ResponseEntity.badRequest().body("User list is empty");
        }
        ArrayList<String >strings=userService.updateUsers(userdtos);
        if (strings.isEmpty()){
            return ResponseEntity.badRequest().body("Failed to update users");
        }else return ResponseEntity.ok("Users updated successfully: "+strings);

    }
    @DeleteMapping()
    public ResponseEntity<String> deleteAllUsers(@RequestBody List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no users provided");
        }
        String result = userService.deleteUsers(ids);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" could not delete Id " + result);

    }

}
