package com.xworkz.ecomerce.service;



import com.xworkz.ecomerce.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    String saveAndValidate(UserDto dto);

    List<UserDto> findAll();

    void deleteUser(int id);

    String updateUser(UserDto dto);



}
