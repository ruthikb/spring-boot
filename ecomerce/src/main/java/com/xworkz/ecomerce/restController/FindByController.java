package com.xworkz.ecomerce.restController;

import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import com.xworkz.ecomerce.service.FindByService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
@Transactional
public class  FindByController {

    private final FindByService findByService;

    public FindByController(FindByService findByService){
        this.findByService = findByService;
    }

    @GetMapping("name")
    public ResponseEntity<List<List<AddressDto>>> findUserByName(@RequestParam String name) {
        List<List<AddressDto>> addressDtos = findByService.findByName(name);
        return ResponseEntity.ok(addressDtos);
    }

    @GetMapping("findAll")
    public Page<UserDto> findAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page,size);
        return findByService.findAllUsers(pageable);
    }

    @GetMapping("findById")
    public ResponseEntity<UserEntity> findById(String id){
        UserEntity byId = findByService.findById(id);
        if (byId == null){
            throw new NoSuchElementException("could not find the user");
        }
        return ResponseEntity.ok(byId);
    }
}
