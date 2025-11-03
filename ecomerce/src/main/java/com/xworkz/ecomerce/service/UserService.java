package com.xworkz.ecomerce.service;

import com.xworkz.ecomerce.dto.UserDto;

public interface UserService {

    String registerUser(UserDto userDto);

    UserDto getUserById(int id);

    UserDto updateUser(int id,UserDto userDto);

    boolean deleteUser(int id);
}
