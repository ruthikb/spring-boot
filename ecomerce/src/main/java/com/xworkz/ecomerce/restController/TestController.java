package com.xworkz.ecomerce.restController;



import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.service.H2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/h2Users")
public class TestController {

    @Autowired
    H2UserService h2UserService;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDto dto){
        h2UserService.saveUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body("User Saved");
    }
}
