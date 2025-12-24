package com.xworkz.ecomerce.restController;


import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    public UserController(UserService service){
        this.service=service;
    }

    @Operation(
            summary = "Create a new user",
            description = "Registers a new user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully saved"),
            @ApiResponse(responseCode = "400", description = "Validation failed. Input contains errors")
    })
    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody @Valid @Parameter(description = "User details for registration", required = true) UserDto dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
        }
        String s = service.saveAndValidate(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("user saved");
    }

    @GetMapping
    public ResponseEntity<String> findAll(){
        List<UserDto> all = service.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(all.toString());
    }
    @DeleteMapping
    public ResponseEntity<String> delete(int id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("deleted");
    }
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserDto dto ,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
        }
        String result = service.updateUser(dto);
        if (result.equals("no id")){
            return ResponseEntity.status(HttpStatus.OK).body("wrong id");
        }
        return ResponseEntity.status(HttpStatus.OK).body("updated");
    }

}
