package com.xworkz.ecomerce.restController;


import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService service;


//    @PostMapping
//    public ResponseEntity<String> createUsers(@RequestBody UserDataWrapper userDataWrapper) {
//        List<UserDto> userDtos = userDataWrapper.getUserDtos();
//        if (userDtos == null || userDtos.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no users provided");
//        }
//        String result = service.saveUsers(userDtos);
//        if ("notOK".equals(result)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not Ok");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body("user saved");
//    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody List<UserDto> userDtos) {

        if (userDtos == null || userDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no users provided");
        }
        String result = service.saveUsers(userDtos);
        if ("notOK".equals(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not Ok");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("user saved");
    }

    @PutMapping
    public ResponseEntity<String> updateUsers(@RequestBody List<UserDto> userDtos) {
        if (userDtos == null || userDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no users provided");
        }
        ArrayList<String> strings = service.updateUsers(userDtos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not update Id " + strings);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUsers(@RequestBody List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no users provided");
        }
        String result = service.deleteUsers(ids);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" could not delete Id " + result);
    }
}