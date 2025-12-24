package com.xworkz.ecomerce.service;


import com.xworkz.ecomerce.dto.AddressDto;
import com.xworkz.ecomerce.dto.UserDto;
import com.xworkz.ecomerce.mysql.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindByService {

    List<List<AddressDto>> findByName(String name);

    Page<UserDto> findAllUsers(Pageable pageable);

    UserEntity findById(String id);

}
